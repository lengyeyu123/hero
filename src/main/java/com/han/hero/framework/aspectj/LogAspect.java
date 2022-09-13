package com.han.hero.framework.aspectj;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.han.hero.common.enums.StateEnums;
import com.han.hero.common.util.JsonUtil;
import com.han.hero.common.util.ServletUtil;
import com.han.hero.framework.annotation.Log;
import com.han.hero.framework.manager.AsyncManager;
import com.han.hero.framework.manager.factory.AsyncFactory;
import com.han.hero.framework.security.LoginUser;
import com.han.hero.framework.security.SecurityUtil;
import com.han.hero.project.domain.OperLog;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;
import java.util.TimeZone;

@Slf4j
@Aspect
@Component
public class LogAspect {

    /**
     * 排除敏感字段
     */
    public static final String[] EXCLUDE_PROPERTIES = {"password", "oldPassword", "newPassword", "confirmPassword"};

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Log controllerLog, Object jsonResult) {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Log controllerLog, Exception e) {
        handleLog(joinPoint, controllerLog, e, null);
    }


    protected void handleLog(JoinPoint joinPoint, Log controllerLog, final Exception e, Object jsonResult) {
        try {
            LoginUser loginUser = SecurityUtil.getLoginUser();

            // ======数据库日志======
            OperLog operLog = new OperLog();
            operLog.setState(StateEnums.ENABLED);
            operLog.setOperIp(ServletUtil.getClientIP(ServletUtil.getRequest()));
            operLog.setOperUrl(ServletUtil.getRequest().getRequestURI());
            if (loginUser != null) {
                operLog.setOperName(loginUser.getUsername());
            }

            if (e != null) {
                operLog.setState(StateEnums.DISABLED);
                operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            // 设置方法名
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operLog.setMethod(className + "." + methodName + "()");
            // 设置请求方式
            operLog.setRequestMethod(ServletUtil.getRequest().getMethod());
            // 处理设置注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, operLog, jsonResult);
            // 保存数据库
            AsyncManager.me().execute(AsyncFactory.recordOper(operLog));
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息: {}", exp.getMessage());
        }

    }

    /**
     * 获取注解中对方法的描述 用于controller层注解
     *
     * @param logg    日志
     * @param operLog 操作日志
     */
    private void getControllerMethodDescription(JoinPoint joinPoint, Log logg, OperLog operLog, Object jsonResult) throws Exception {
        // 设置action动作
        operLog.setBusinessType(logg.businessType());
        // 设置标题
        operLog.setTitle(logg.title());
        // 是否需要保存request，参数和值
        if (logg.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, operLog);
        }
        // 是否需要保存返回结果
        if (logg.isSaveResponseData() && jsonResult != null) {
            operLog.setJsonResult(StringUtils.substring(JsonUtil.toJson(jsonResult), 0, 2000));
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operLog 操作日志
     * @throws Exception 异常
     */
    private void setRequestValue(JoinPoint joinPoint, OperLog operLog) throws Exception {
        String requestMethod = operLog.getRequestMethod();
        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            String params = argsArrayToString(joinPoint.getArgs());
            operLog.setOperParam(StringUtils.substring(params, 0, 2000));
        } else {
            Map<?, ?> paramsMap = (Map<?, ?>) ServletUtil.getRequest().getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            operLog.setOperParam(StringUtils.substring(paramsMap.toString(), 0, 2000));
        }
    }

    // public static void main(String[] args) {
    //     class My{
    //         public String userName;
    //
    //         public String password;
    //     }
    //     My my = new My();
    //     my.userName = "张三";
    //     my.password = "123";
    //     My[] mies = {my};
    //     System.out.println(new LogAspect().argsArrayToString(mies));
    //
    // }

    /**
     * 参数拼装
     */
    @SneakyThrows
    private String argsArrayToString(Object[] paramsArray) {
        ObjectMapper objectMapper = new ObjectMapper();
        // 时间格式 时区
        objectMapper.setDateFormat(new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN));
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        SimpleBeanPropertyFilter fieldFilter = SimpleBeanPropertyFilter.serializeAllExcept(EXCLUDE_PROPERTIES);
        SimpleFilterProvider filterProvider = new SimpleFilterProvider().addFilter("fieldFilter", fieldFilter);
        objectMapper.setFilterProvider(filterProvider);

        StringBuilder params = new StringBuilder();
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                if (o != null && !isFilterObject(o)) {
                    objectMapper.addMixIn(o.getClass(), FieldFilterMixIn.class);
                    params.append(objectMapper.writeValueAsString(o));
                }
            }
        }
        return params.toString().trim();
    }

    @JsonFilter("fieldFilter")
    interface FieldFilterMixIn {
    }

    /**
     * 判断是否为需要过滤的对象
     *
     * @param o 对象信息
     * @return 如果是需要过滤的对象，则返回true，否则返回false。
     */
    @SuppressWarnings("rawtypes")
    private boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Object value : collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse || o instanceof BindResult;
    }


}
