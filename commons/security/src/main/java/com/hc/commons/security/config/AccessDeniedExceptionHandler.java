package com.hc.commons.security.config;

import com.hc.commons.security.beans.handlers.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class AccessDeniedExceptionHandler {

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @ExceptionHandler(AccessDeniedException.class)
    public void handleException(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws Exception {
        accessDeniedHandler.handle(request, response, e);
    }

}
