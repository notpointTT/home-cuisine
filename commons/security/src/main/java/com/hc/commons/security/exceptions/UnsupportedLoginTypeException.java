package com.hc.commons.security.exceptions;


import com.hc.commons.dto.emums.ApiResultCode;

public class UnsupportedLoginTypeException extends AuthException {
    public UnsupportedLoginTypeException() {
        super(ApiResultCode.UNSUPPORTED_LOGIN_TYPE);
    }
}
