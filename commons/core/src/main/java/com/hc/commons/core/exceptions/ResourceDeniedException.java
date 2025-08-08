package com.hc.commons.core.exceptions;

public class ResourceDeniedException extends BaseException{

    public ResourceDeniedException() {
        super("访问被拒绝", 403);
    }
}
