package com.han.hero.framework.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 安全服务工具类
 */
public class SecurityUtil {

    /**
     * 获取用户ID
     */
    public static Integer getUserId() {
        return getLoginUser().getUser().getId();
    }

    /**
     * 获取用户名
     */
    public static String getUserName() {
        return getLoginUser().getUser().getUserName();
    }

    public static LoginUser getLoginUser() {
        return (LoginUser) getAuthentication().getPrincipal();
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
