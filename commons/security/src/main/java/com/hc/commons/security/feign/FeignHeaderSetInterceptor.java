package com.hc.commons.security.feign;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.hc.commons.dto.constant.CommonConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author a1234
 * @description
 * @create 2025-08-07 17:31
 */
@Component
public class FeignHeaderSetInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            String accessToken = attributes.getRequest().getHeader(CommonConstant.Headers.TOKEN_HEADER);
            String userId = attributes.getRequest().getHeader(CommonConstant.Headers.X_USER_ID_HEADER);
            String userRoles = attributes.getRequest().getHeader(CommonConstant.Headers.X_USER_ROLES_HEADER);

            String gTraceId = attributes.getRequest().getHeader(CommonConstant.Headers.X_G_TRACE_ID_HEADER);
            if (StringUtils.isEmpty(gTraceId)) {
                gTraceId = UuidUtils.generateUuid();
            }

            template.header(CommonConstant.Headers.TOKEN_HEADER, accessToken);
            template.header(CommonConstant.Headers.X_USER_ID_HEADER, userId);
            template.header(CommonConstant.Headers.X_USER_ROLES_HEADER, userRoles);
            template.header(CommonConstant.Headers.X_REQUEST_FROM_HEADER, CommonConstant.Headers.X_REQUEST_FROM_FEIGN);
            template.header(CommonConstant.Headers.X_G_TRACE_ID_HEADER, gTraceId);
        }
    }
}
