package com.xxx.common.exceptions;

/**
 * @author a1234
 * @description
 * @create 2023-12-29 10:50
 */
public class XxxException extends RuntimeException{

    public XxxException() {
    }

    public XxxException(String message) {
        super(message);
    }

    public XxxException(String message, Throwable cause) {
        super(message, cause);
    }

    public XxxException(Throwable cause) {
        super(cause);
    }

}
