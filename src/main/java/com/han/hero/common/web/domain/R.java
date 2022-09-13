package com.han.hero.common.web.domain;

import com.han.hero.common.enums.ResultStatus;
import com.han.hero.common.exception.ServiceException;
import lombok.Data;

@Data
public class R<T> {

    /**
     * 状态码
     */
    private int code;

    /**
     * 前端通知消息
     */
    private String message;

    /**
     * 默认消息，异常携带信息
     */
    private String defaultMsg;

    private T data;

    public static <T> R<T> ok() {
        R<T> r = new R<>();
        r.setCode(ResultStatus.OK.getCode());
        r.setMessage(ResultStatus.OK.getMsg());
        return r;
    }

    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setCode(ResultStatus.OK.getCode());
        r.setMessage(ResultStatus.OK.getMsg());
        r.setData(data);
        return r;
    }

    public static <T> R<T> fail(ResultStatus resultStatus) {
        R<T> r = new R<>();
        r.setCode(resultStatus.getCode());
        r.setMessage(resultStatus.getMsg());
        return r;
    }

    public static <T> R<T> fail(ServiceException exception) {
        R<T> r = new R<>();
        r.setCode(exception.getStatus().getCode());
        r.setMessage(exception.getStatus().getMsg());
        // 不是业务系统异常， 1/0 NullPointException 等
        r.setDefaultMsg(exception.getDefaultMsg());
        return r;
    }

    public static <T> R<T> fail(Throwable exception) {
        R<T> r = new R<>();
        r.setCode(ResultStatus.SERVER_ERROR.getCode());
        r.setMessage(ResultStatus.SERVER_ERROR.getMsg());
        r.setDefaultMsg(exception.getMessage());
        return r;
    }

}
