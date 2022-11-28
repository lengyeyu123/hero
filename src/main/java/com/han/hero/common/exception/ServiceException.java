package com.han.hero.common.exception;

import com.han.hero.common.enums.ResultStatus;
import lombok.Getter;

/**
 * 业务异常
 */
@Getter
public class ServiceException extends RuntimeException {

    private final ResultStatus status;

    /**
     * 给前台的提示消息
     */
    private final String msg;

    public ServiceException(ResultStatus status) {
        this.status = status;
        this.msg = status.getMsg();
    }


    public ServiceException(ResultStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

}
