package com.hc.commons.core.exceptions;

import org.springframework.http.HttpStatus;

/**
 * @author a1234
 * @description
 * @create 2025-08-08 10:26
 */
public class TooManyRequestException extends BaseException {

    public TooManyRequestException() {
        super("请求过于频繁", 429);
    }

}
