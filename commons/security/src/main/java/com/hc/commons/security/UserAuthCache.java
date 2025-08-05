package com.hc.commons.security;

import com.alibaba.fastjson.JSON;
import com.hc.commons.security.model.AuthUserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class UserAuthCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthCache.class);

    private static final String AUTH_REDIS_PREFIX = "USER:AUTH::";
    /**
     * 超时时间应与 RefreshToken 一致
     * 此为 12 小时
     */
    private static final long ROLE_TIME_OUT_SECOND = 12 * 60 * 60;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void setUserAuth(String username, AuthUserInfo userInfo) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(getUserAuthKey(username), JSON.toJSONString(userInfo), ROLE_TIME_OUT_SECOND, TimeUnit.SECONDS);
    }

    public AuthUserInfo getUserAuth(String username) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String rolesJson = ops.get(getUserAuthKey(username));

        return JSON.parseObject(rolesJson, AuthUserInfo.class);
    }


    String getUserAuthKey(String username) {
        return AUTH_REDIS_PREFIX + username;
    }

}
