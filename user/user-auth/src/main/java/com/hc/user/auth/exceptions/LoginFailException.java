package com.hc.user.auth.exceptions;

import com.hc.common.emums.ApiResultCode;

public class LoginFailException extends AuthException {
    public LoginFailException() {
        super(ApiResultCode.LOGIN_FAIL);
    }

}
