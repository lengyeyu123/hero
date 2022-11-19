package com.han.hero.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public enum MenuType implements BaseEnum {

    M(0, "目录"),

    C(1, "菜单"),

    F(2, "按钮");

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

    MenuType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static MenuType getByCode(Integer code) {
        for (MenuType value : MenuType.values()) {
            if (Objects.equals(value.code, code)) {
                return value;
            }
        }
        return null;
    }
}
