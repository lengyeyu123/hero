package com.han.hero.common.enums;

import lombok.Getter;

@Getter
public enum ResultStatus {

    OK(2000, "成功"),

    SERVER_ERROR(5000, "服务器内部错误"),

    // UNAUTHORIZED(4010, "未认证"),

    // FORBIDDEN(4030, "未授权");

    // ------ token
    TOKE_EXPIRED(9999, "令牌过期"),

    // ------- 账户
    ACCOUNT_DISABLED(5001, "账户以被禁用"),

    ACCOUNT_ERROR_USER_NAME_OR_PASSWORD(5002, "用户名密码错误"),

    ACCOUNT_USER_NAME_REPEAT(5003, "用户名已被占用"),

    // ------ 上传文件内容 或提交表单内容
    CONTENT_UN_SAFE(5102, "内容不安全");

    private final Integer code;

    private final String message;

    ResultStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}