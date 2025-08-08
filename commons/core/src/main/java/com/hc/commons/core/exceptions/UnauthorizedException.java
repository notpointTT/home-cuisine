package com.hc.commons.core.exceptions;

public class UnauthorizedException extends BaseException {

    public UnauthorizedException() {
        this("认证失败");
    }

    public UnauthorizedException(String message) {
        super(message, 401);
    }
}
