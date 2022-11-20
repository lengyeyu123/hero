package com.han.hero.framework.datasource;

import com.han.hero.common.constants.ClaimsConstants;
import com.han.hero.common.util.JwtUtil;
import com.han.hero.framework.config.properties.TokenProperties;
import com.han.hero.framework.db.InitDB;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 根据请求头中的机构代码切换数据源
 */
@Slf4j
@Component
public class DynamicDataSourceHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private DynamicDataSourceConfig dynamicDataSourceConfig;

    // 在handler方法执行之前（简单理解为Controller提供的服务调用之前）会被触发，如果返回ture，表示拦截通过，可以执行；若果返回false，表示不允许往后走
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TokenProperties.TokenConfig atConfig = tokenProperties.getAtConfig();
        String header = request.getHeader(atConfig.getHeader());
        if (StringUtils.isNotBlank(header)) {
            Claims claims;
            try {
                claims = JwtUtil.parseJwt(header, atConfig.getSecret());
                String organCode = (String) claims.get(ClaimsConstants.ORGAN_CODE_KEY);
                if (StringUtils.isNotBlank(organCode)) {
                    String dbName = InitDB.organDbName(organCode);
                    dynamicDataSourceConfig.addDataSource(dbName, true);
                }
            } catch (Exception e) {
                log.error("token解析失败", e);
            }
        }
        return true;
    }

    // 这个是在handler方法执行之后，视图渲染之前被回调，简单来说，我们在这个时候，是可以操作ModelAndView，往里面添加一下信息，并能被视图解析渲染的
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    // 顾名思义，该方法将在整个请求结束之后，也就是在 DispatcherServlet 渲染了对应的视图之后执行。此方法主要用来进行资源清理
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
