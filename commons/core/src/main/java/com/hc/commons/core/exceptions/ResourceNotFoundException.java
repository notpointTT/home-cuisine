package com.hc.commons.core.exceptions;

public class ResourceNotFoundException extends BaseException{
    public ResourceNotFoundException() {
        super("未找到资源", 404);
    }
}
