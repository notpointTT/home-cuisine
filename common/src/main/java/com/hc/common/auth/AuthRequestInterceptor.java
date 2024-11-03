package com.hc.common.auth;

import com.alibaba.fastjson.JSON;
import com.hc.common.annotation.IgnoreAuth;
import com.hc.common.constant.CommonConstant;
import com.hc.common.emums.ApiResultCode;
import com.hc.common.exceptions.ResourceAccessDeniedException;
import com.hc.common.model.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AuthRequestInterceptor implements HandlerInterceptor {

    public static final String URL_PER_PREFIX = "URL_PER::";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserTokenCache userTokenCache;

    @Value("spring.application.name")
    private String appId;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 只处理 HandlerMethod 的请求类型
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Object bean = handlerMethod.getBean();
        if (bean instanceof BasicErrorController) {
            // 系统自带异常处理 Controller 不做权限校验
            return true;
        }

        boolean ignoreAuth = isIgnoreAuth(handlerMethod);
        if (ignoreAuth) {
            return true;
        }

        // 获取当前请求地址
        String requestUrl = request.getRequestURL().toString();
        // checkPermission
        try {
            return checkPermission(requestUrl);
        } catch (Exception e) {
            writeAccessDeniedResponse(response);
            return false;
        }

    }


    /**
     *
     * @param requestUrl
     * @return true -> 校验通过； false -> 校验不通过
     */
    boolean checkPermission(String requestUrl) {
        AuthUserInfo authUserInfo = userTokenCache.currentUser();
//        List<String> authorities = authUserInfo.getAuthorities();
//        int indexOf = authorities.indexOf(requestUrl);
//        if (indexOf >= 0) {
//            return true;
//        }
        if (authUserInfo != null) {
            return true;
        }
        throw new ResourceAccessDeniedException();
    }

    void writeAccessDeniedResponse(HttpServletResponse response) throws Exception {
        response.setContentType("application/json");
        response.getOutputStream().write(
                JSON.toJSONString(
                        ApiResult.result(
                                ApiResultCode.ACCESS_DENIED.getCode(),
                                ApiResultCode.ACCESS_DENIED.getMsg(),
                                null
                        )
                ).getBytes(StandardCharsets.UTF_8)
        );
    }

    boolean isIgnoreAuth(HandlerMethod handlerMethod) {

        Method method = handlerMethod.getMethod();
        IgnoreAuth methodIgnoreAuth = method.getAnnotation(IgnoreAuth.class);
        if (methodIgnoreAuth != null) {
            return true;
        }

        Class<?> beanType = handlerMethod.getBeanType();
        IgnoreAuth beanIgnoreAuth = beanType.getAnnotation(IgnoreAuth.class);
        if (beanIgnoreAuth != null) {
            return true;
        }

        return false;
    }

}
