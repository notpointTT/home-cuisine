package com.hc.user.auth.web;

import com.hc.common.model.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class AccessDeniedRestAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    public ApiResult<?> handleException(Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        return ApiResult.error("无权限访问");
    }

}
