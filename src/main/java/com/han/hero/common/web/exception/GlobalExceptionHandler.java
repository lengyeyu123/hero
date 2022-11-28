package com.han.hero.common.web.exception;

import com.han.hero.common.exception.ServiceException;
import com.han.hero.common.web.domain.R;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;


/**
 * Controller 抛出异常拦截处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 401 403
    // 由于 认证过滤器 与 授权过滤器位置不同 401不会被捕获 而403会被捕获 直接向上抛出异常由spring security处理即可

    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDeniedException(AccessDeniedException accessDeniedException) {
        log.info("未授权", accessDeniedException);
        throw accessDeniedException;
    }

    /**
     * 未知异常
     */
    @ExceptionHandler(Throwable.class)
    public R<?> handleException(Throwable e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生未知异常", requestURI);
        log.error(e.getMessage(), e);
        return R.fail(e);
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<?> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
        return R.fail(e);
    }

    /**
     * 参数验证异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',参数验证未通过", requestURI);
        return R.fail(e);
    }

    /**
     * 上传文件大小超过限制
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public R<?> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',上传文件大小超过限制", requestURI);
        return R.fail(e);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public R<?> handleServiceException(ServiceException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生业务异常", requestURI);
        log.error(e.getMessage(), e);
        return R.fail(e);
    }


}
