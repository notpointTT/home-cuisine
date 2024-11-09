package com.hc.user.core.oauth.exceptions;

import org.springframework.security.core.AuthenticationException;

public class LoginFailException extends AuthenticationException {
    public LoginFailException(String msg) {
        super(msg);
    }
}
