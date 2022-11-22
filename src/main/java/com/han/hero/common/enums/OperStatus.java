package com.han.hero.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public enum OperStatus implements BaseEnum {

    Normal(1, "正常"),

    Abnormal(0, "异常");

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


    OperStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static OperStatus getByCode(Integer code) {
        for (OperStatus value : OperStatus.values()) {
            if (Objects.equals(value.code, code)) {
                return value;
            }
        }
        return null;
    }
}
