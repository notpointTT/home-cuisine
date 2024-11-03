package com.hc.common.exceptions;

import com.hc.common.constant.CommonConstant;
import com.hc.common.emums.ApiResultCode;

import javax.validation.constraints.NotNull;

/**
 * @author a1234
 * @description
 * @create 2023-12-29 10:50
 */
public class BaseException extends RuntimeException{

    private ApiResultCode code = ApiResultCode.ERROR;

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public ApiResultCode getCode() {
        return code;
    }

    public void setCode(@NotNull ApiResultCode code) {
        if (code == null) {
            throw new RuntimeException("code is not null");
        }
        this.code = code;
    }
}
