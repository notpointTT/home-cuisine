package com.xxx.common.web;

import com.xxx.common.exceptions.BaseException;
import com.xxx.common.model.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author a1234
 * @description
 * @create 2023-12-29 10:43
 */
@RestControllerAdvice
public class RestExceptionHandle {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandle.class);

    @ExceptionHandler(Exception.class)
    public ApiResult<?> handleException(Exception e) {
        LOGGER.error("发生未知异常", e);
        return ApiResult.error("发生未知异常 -->> " + e.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public ApiResult<?> handle3xException(BaseException e) {
        String message = e.getMessage();
        if (StringUtils.isEmpty(message)) {
            message = "发生未知异常-3x";
        }
        LOGGER.error(message, e);
        return ApiResult.error(message);
    }

}
