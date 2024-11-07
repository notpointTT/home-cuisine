package com.hc.common.verification;

import com.hc.common.exceptions.BaseException;
import com.hc.common.exceptions.VerificationCodeSendException;
import com.hc.common.utils.ShortMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class VerificationCodeHandler {

    /**
     * 验证码过期时间
     * 秒
     */
    private static final long CODE_TIME_OUT_SECOND = 60;

    private static final String CODE_REDIS_PREFIX = "V_CODE::";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void sendCode(String phoneNum, String code, String type) {
//        String code = "9527";
        String codeKey = keyPrefix(type) + phoneNum;
        // Redis 缓存验证码
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        Boolean res = ops.setIfAbsent(codeKey, code, CODE_TIME_OUT_SECOND, TimeUnit.SECONDS);
        // 验证 是否缓存成功
        if (res == null || Boolean.FALSE.equals(res)) {
//            throw new BaseException("请勿频繁发送");
            return;
        }

        // 发送验证码
        String verificationCode = sendCode0(phoneNum, code, type);
        if (!code.equals(verificationCode)) {
            // 验证码发送异常，清理本次所缓存验证码
            redisTemplate.delete(codeKey);
            throw new VerificationCodeSendException();
        }

        // 发送成功
    }

    public boolean codeAvailable(String phoneNum, String code, String type) {
        String codeKey = keyPrefix(type) + phoneNum;
        Long expire = redisTemplate.getExpire(codeKey);
        // 过期时间 大于0
        return expire != null && expire > 0;
    }

    String sendCode0(String phoneNum, String code, String type) {

        return ShortMsgUtil.sendVerificationCode(type, phoneNum, code);
    }

    String keyPrefix(String type) {
        return CODE_REDIS_PREFIX + type + "::";
    }

}
