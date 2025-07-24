package com.hc.common.utils;

import com.hc.common.constant.CommonConstant;
import com.hc.common.exceptions.BaseException;
import com.hc.common.exceptions.UserNotLoginException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author a1234
 * @description
 * @create 2025-07-24 17:37
 */
public class SecurityUtil {

    public static String currentToken() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new BaseException("请求信息获取异常");
        }
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader(CommonConstant.TOKEN_HEADER_NAME);
        if (StringUtils.isEmpty(token)) {
            throw new UserNotLoginException();
        }
        return token;
    }

}
