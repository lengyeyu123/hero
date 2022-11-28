package com.han.hero.common.enums;

import lombok.Getter;

/**
 * 成功 0
 * 通用错误码 5000
 * 登录认证权限 6000
 * 基础信息 7000 (院系 专业 班级 教师 学生……)
 * 考试设置 8000
 * 阅卷 9000
 * 统计信息 2000
 */
@Getter
public enum ResultStatus {

    // -----------成功-----------
    OK(200, "成功"),

    // -----------COMMON_ 通用错误码 5000-----------
    COMMON_SERVER_ERROR(5000, "服务器内部错误"),

    // 上传文件内容 或提交表单内容
    COMMON_CONTENT_UN_SAFE(5001, "内容不安全"),

    COMMON_DATA_HAS_USED(5002, "数据已被使用，请删除关联数据后进行删除"),

    COMMON_DATA_NOT_EXIST(5003, "数据不存在"),

    COMMON_PARAMS_NULL(5004, "参数异常"),

    COMMON_DATA_NOT_UNIQUE(5005, "数据不唯一"),

    // -----------AUTH_ 登录认证权限 6000-----------
    AUTH_TOKEN_EXPIRED(6000, "令牌过期"),

    AUTH_ACCOUNT_DISABLED(6001, "账户以被禁用"),

    AUTH_ACCOUNT_ERROR_USER_NAME_OR_PASSWORD(5002, "用户名密码错误"),

    AUTH_ACCOUNT_USER_NAME_REPEAT(5003, "用户名已被占用"),

    // -----------BASE_ 基础信息 7000 (院系 专业 班级 教师 学生……)-----------
    // -----------EXAM_ 考试设置 8000-----------
    // -----------阅卷 9000-----------
    // -----------REPORT_ 统计信息 2000-----------
    ;

    private final Integer code;

    private final String msg;

    ResultStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
