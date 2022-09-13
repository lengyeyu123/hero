package com.han.hero.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

public enum StateEnums {
    ENABLED(1, "正常"),
    DISABLED(0, "禁用"),
    ;

    private final Integer code;

    private final String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    StateEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static StateEnums get(Integer code) {
        for (StateEnums value : StateEnums.values()) {
            if (Objects.equals(value.code, code)) {
                return value;
            }
        }
        return null;
    }

}
