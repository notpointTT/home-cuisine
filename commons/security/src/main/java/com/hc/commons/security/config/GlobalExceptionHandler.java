package com.hc.commons.security.config;

import com.hc.commons.core.exceptions.BaseException;
import com.hc.commons.dto.emums.ApiResultCode;
import com.hc.commons.dto.response.ApiResult;
import feign.FeignException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * @author a1234
 * @description
 * @create 2023-12-29 10:43
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ApiResult<?> handleException(Exception e) {
        LOGGER.error("发生未知异常", e);
        return ApiResult.error("发生未知异常 -->> " + e.getMessage());
    }

    @ExceptionHandler(BaseException.class)
    public ApiResult<?> handle3xException(BaseException e) {
        String message = e.getMessage();
        ApiResultCode code = e.getCode();
        if (StringUtils.isEmpty(message)) {
            String msg = code.getMsg();
            if (StringUtils.isEmpty(msg)) {
                message = "HC-发生未知异常";
            }else {
                message = msg;
            }

        }
        LOGGER.error(message, e);
        return ApiResult.result(code.getCode(), message, null);
    }

    @ExceptionHandler(FeignException.class)
    public void feignExceptionHandler(FeignException e, HttpServletResponse response) {
        int status = e.status();
        response.setStatus(status);
    }

}
