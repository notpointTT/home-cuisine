package com.hc.user.core.oauth.exceptions;

import com.hc.common.emums.ApiResultCode;
import org.springframework.security.core.AuthenticationException;

public class LoginFailException extends AuthException {
    public LoginFailException() {
        super(ApiResultCode.LOGIN_FAIL);
    }

}
