package com.hc.user.core.oauth.exceptions;

import com.hc.common.emums.ApiResultCode;
import org.springframework.security.core.AuthenticationException;

public class UnsupportedLoginTypeException extends AuthException {
    public UnsupportedLoginTypeException() {
        super(ApiResultCode.UNSUPPORTED_LOGIN_TYPE);
    }
}
