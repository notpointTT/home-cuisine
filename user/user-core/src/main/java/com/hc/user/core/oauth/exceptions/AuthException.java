package com.hc.user.core.oauth.exceptions;

import com.hc.common.emums.ApiResultCode;
import org.springframework.security.core.AuthenticationException;

public class AuthException extends AuthenticationException {
    private final ApiResultCode resultCode;

    public AuthException(ApiResultCode resultCode) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

    public ApiResultCode getResultCode() {
        return resultCode;
    }
}
