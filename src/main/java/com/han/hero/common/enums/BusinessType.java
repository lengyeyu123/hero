package com.han.hero.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

/**
 * 业务操作类型
 */
public enum BusinessType implements BaseEnum {

    /**
     * 其他
     */
    OTHER(9, "其他"),

    /**
     * 新增
     */
    INSERT(0, "新增"),

    /**
     * 修改
     */
    UPDATE(1, "修改"),

    /**
     * 删除
     */
    DELETE(2, "删除"),

    /**
     * 授权
     */
    GRANT(3, "授权"),

    /**
     * 导出
     */
    EXPORT(4, "导出"),

    /**
     * 导入
     */
    IMPORT(5, "导入"),

    /**
     * 强退
     */
    FORCE(6, "强退"),

    /**
     * 清空数据
     */
    CLEAN(8, "清空数据"),
    ;

    private final Integer code;

    private final String msg;

    @JsonValue
    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    BusinessType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static BusinessType getByCode(Integer code) {
        for (BusinessType value : BusinessType.values()) {
            if (Objects.equals(value.code, code)) {
                return value;
            }
        }
        return null;
    }
}
