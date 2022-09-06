package com.han.hero.framework.annotation;

import com.han.hero.common.enums.BusinessType;

import java.lang.annotation.*;

/**
 * 自定义操作日志处理注解
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;

    /**
     * 是否保存响应数据
     */
    public boolean isSaveResponseData() default true;

}
