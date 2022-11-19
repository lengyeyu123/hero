package com.han.hero.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public enum OpenStatus implements BaseEnum {

    Open(1, "开启"),

    Close(0, "关闭");

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


    OpenStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static OpenStatus getByCode(Integer code) {
        for (OpenStatus value : OpenStatus.values()) {
            if (Objects.equals(value.code, code)) {
                return value;
            }
        }
        return null;
    }

}
