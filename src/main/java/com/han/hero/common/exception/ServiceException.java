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
     * 异常真实  详细错误信息
     */
    private final String defaultMsg;

    public ServiceException(ResultStatus status) {
        this.status = status;
        this.defaultMsg = status.getMsg();
    }

    public ServiceException(ResultStatus status, String defaultMsg) {
        this.status = status;
        this.defaultMsg = defaultMsg;
    }


}
