package com.hc.commons.security.web;

import com.hc.common.model.ApiResult;
import com.hc.user.auth.beans.handlers.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import com.hc.commons.dto.response.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class AccessDeniedRestAdvice {

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @ExceptionHandler(AccessDeniedException.class)
    public void handleException(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws Exception {
        accessDeniedHandler.handle(request, response, e);
    }

}
