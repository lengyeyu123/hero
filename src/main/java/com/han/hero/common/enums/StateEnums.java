package com.han.hero.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public enum StateEnums implements BaseEnum {
    ENABLED(1, "正常"),
    DISABLED(0, "禁用"),
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

    StateEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static StateEnums getByCode(Integer code) {
        for (StateEnums value : StateEnums.values()) {
            if (Objects.equals(value.code, code)) {
                return value;
            }
        }
        return null;
    }

}
