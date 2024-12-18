package com.hc.common.auth;

import com.alibaba.fastjson.JSON;
import com.hc.common.constant.CommonConstant;
import com.hc.common.exceptions.BaseException;
import com.hc.common.exceptions.UserNotLoginException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Component
public class UserTokenCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserTokenCache.class);

    private static final String TOKEN_REDIS_PREFIX = "TOKEN::";
    private static final long TOKEN_TIME_OUT_SECOND = 30 * 60;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public void setToken(String token, AuthUserInfo userInfo) {
        setToken(token, JSON.toJSONString(userInfo));
    }

    private void setToken(String token, String userInfoJson) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String key = TOKEN_REDIS_PREFIX + token;
        ops.set(key, userInfoJson, TOKEN_TIME_OUT_SECOND, TimeUnit.SECONDS);
    }

    public AuthUserInfo currentUser() {
        String currentToken = currentToken();
        return tokenUser(currentToken);
    }

    /**
     * 获取 token 对应的用户信息
     * 每次调用此方法获取信息，都会将token有效期延长 TOKEN_TIME_OUT_SECOND 秒
     * @param token
     * @return
     */
    public AuthUserInfo tokenUser(String token) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String userJson = ops.get(token);
        if (StringUtils.isEmpty(userJson)) {
            throw new UserNotLoginException();
        }
        AuthUserInfo authUserInfo = JSON.parseObject(userJson, AuthUserInfo.class);
        setToken(token, userJson);
        return authUserInfo;
    }

    public String currentToken() {
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
