package com.hc.commons.core.exceptions;


import com.hc.commons.dto.emums.ApiResultCode;

import javax.validation.constraints.NotNull;

/**
 * @author a1234
 * @description
 * @create 2023-12-29 10:50
 */
public class BaseException extends RuntimeException {

    private int responseStatus = 500;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, int responseStatus) {
        super(message);
        this.responseStatus = responseStatus;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }
}
