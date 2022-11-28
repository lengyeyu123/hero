package com.han.hero.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public enum DelFlag implements BaseEnum {

    NORMAL(0, "未删除"),

    Del(1, "已删除"),
    ;

    @JsonValue
    private final Integer code;

    private final String msg;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    DelFlag(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static DelFlag getByCode(Integer code) {
        for (DelFlag value : DelFlag.values()) {
            if (Objects.equals(value.code, code)) {
                return value;
            }
        }
        return null;
    }

}
