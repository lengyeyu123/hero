package com.han.hero.common.enums;

import lombok.Getter;

/**
 * 通用错误码 5000
 * 登录认证权限 6000
 * 基础信息 7000 (院系 专业 班级 教师 学生……)
 * 考试设置 8000
 * 阅卷 9000
 * 统计信息 2000
 */
@Getter
public enum ResultStatus {

    OK(0, "成功"),

    SERVER_ERROR(5000, "服务器内部错误"),

    // UNAUTHORIZED(4010, "未认证"),

    // FORBIDDEN(4030, "未授权");

    // ------ token
    TOKEN_EXPIRED(9999, "令牌过期"),

    // ------- 账户
    ACCOUNT_DISABLED(5001, "账户以被禁用"),

    ACCOUNT_ERROR_USER_NAME_OR_PASSWORD(5002, "用户名密码错误"),

    ACCOUNT_USER_NAME_REPEAT(5003, "用户名已被占用"),

    // ------ 上传文件内容 或提交表单内容
    CONTENT_UN_SAFE(5102, "内容不安全"),

    // ------ 一般业务异常 59
    DATA_HAS_USED(5901, "数据已被使用，请删除关联数据后进行删除"),

    DATA_NOT_EXIST(5802, "数据不存在"),

    ;

    private final Integer code;

    private final String msg;

    ResultStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
