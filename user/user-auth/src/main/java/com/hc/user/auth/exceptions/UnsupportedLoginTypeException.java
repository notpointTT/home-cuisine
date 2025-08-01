package com.hc.user.auth.exceptions;

import com.hc.common.emums.ApiResultCode;

public class UnsupportedLoginTypeException extends AuthException {
    public UnsupportedLoginTypeException() {
        super(ApiResultCode.UNSUPPORTED_LOGIN_TYPE);
    }
}
