package com.hc.commons.security.exceptions;


import com.hc.commons.dto.emums.ApiResultCode;

public class LoginFailException extends AuthException {
    public LoginFailException() {
        super(ApiResultCode.LOGIN_FAIL);
    }

}
