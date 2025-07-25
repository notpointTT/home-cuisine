package com.hc.user.core.oauth;

import com.alibaba.fastjson.JSON;
import com.hc.common.constant.CommonConstant;
import com.hc.common.exceptions.BaseException;
import com.hc.common.exceptions.UserNotLoginException;
import com.hc.common.utils.SecurityUtil;
import com.hc.user.core.model.auth.AuthUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
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
        ops.set(getTokenKey(token), userInfoJson, TOKEN_TIME_OUT_SECOND, TimeUnit.SECONDS);
    }

    /**
     * 获取 token 对应的用户信息
     * 每次调用此方法获取信息，都会将token有效期延长 TOKEN_TIME_OUT_SECOND 秒
     * @param token
     * @return
     */
    public AuthUserInfo tokenUser(String token) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String userJson = ops.get(getTokenKey(token));
        if (StringUtils.isEmpty(userJson)) {
            throw new UserNotLoginException();
        }
        AuthUserInfo authUserInfo = JSON.parseObject(userJson, AuthUserInfo.class);
        setToken(token, userJson);
        return authUserInfo;
    }

    String getTokenKey(String token) {
        return TOKEN_REDIS_PREFIX + token;
    }

}
