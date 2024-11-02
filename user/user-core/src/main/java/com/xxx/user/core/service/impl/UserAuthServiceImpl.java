package com.xxx.user.core.service.impl;

import com.xxx.common.exceptions.BaseException;
import com.xxx.common.utils.UUIDUtil;
import com.xxx.user.core.model.auth.UsernameLoginModel;
import com.xxx.user.core.service.UserAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author a1234
 * @description
 * @create 2024-04-25 16:55
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthServiceImpl.class);


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final long CODE_TIME_OUT_SECOND = 10;
    private static final long TOKEN_TIME_OUT_SECOND = 30*60;

    @Override
    public void sendVerificationCode(String phoneNum) {
        // 验证 1 分钟内是否发送过
        LOGGER.info(phoneNum);

        String uuid = UUIDUtil.uuid();
        // Redis 缓存验证码
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        Boolean res = ops.setIfAbsent(phoneNum+":V_CODE", uuid, CODE_TIME_OUT_SECOND, TimeUnit.SECONDS);
        // 验证 是否缓存成功
        if (res == null || Boolean.FALSE.equals(res)) {
            throw new BaseException("请勿频繁发送");
        }

        // todo 发送验证码

        LOGGER.info("{} 验证码 -->> {}", phoneNum, uuid);
    }

    @Override
    public String registerAndLogin(String phoneNum, String verificationCode) {
        // 验证 verificationCode 是否合法
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String v = ops.get(phoneNum);
        if (!Objects.equals(v, verificationCode)) {
            throw new BaseException("验证码无效");
        }

        // 用户信息注册

        // 执行登录操作
        return login(phoneNum, verificationCode);
    }

    @Override
    public String login(String phoneNum, String verificationCode) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String uuid = UUIDUtil.uuid();
        String key = uuid + ":PHONE:TOKEN";
        ops.set(key, phoneNum, TOKEN_TIME_OUT_SECOND, TimeUnit.SECONDS);
        return key;
    }

    @Override
    public String loginUsername(UsernameLoginModel loginModel) {
        String username = loginModel.getUsername();
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String uuid = UUIDUtil.uuid();
        String key = uuid + ":USERNAME:TOKEN";
        ops.set(key, username, TOKEN_TIME_OUT_SECOND, TimeUnit.SECONDS);
        return key;
    }

    @Override
    public String tokenValidate(String token) {
        return null;
    }
}
