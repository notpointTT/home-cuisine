package com.hc.user.core.oauth.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UnsupportedLoginTypeException extends AuthenticationException {
    public UnsupportedLoginTypeException(String msg) {
        super(msg);
    }
}
