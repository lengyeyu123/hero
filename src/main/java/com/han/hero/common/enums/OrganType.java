package com.han.hero.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

public enum OrganType implements BaseEnum {

    UNIVERSITY(0, "大学"),

    COLLAGE(1, "学院"),

    SCHOOL(3, "中小学");

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

    OrganType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static OrganType getByCode(Integer code) {
        for (OrganType value : OrganType.values()) {
            if (Objects.equals(value.code, code)) {
                return value;
            }
        }
        return null;
    }

}
