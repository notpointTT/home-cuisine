package com.hc.commons.core.exceptions;

public class ServiceUnavailableException extends BaseException{
    public ServiceUnavailableException() {
        super("服务不可用", 503);
    }
}
