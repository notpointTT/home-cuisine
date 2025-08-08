package com.hc.commons.core.exceptions;

public class BadRequestException extends BaseException {

    public BadRequestException() {
        super("无效请求", 400);
    }
}
