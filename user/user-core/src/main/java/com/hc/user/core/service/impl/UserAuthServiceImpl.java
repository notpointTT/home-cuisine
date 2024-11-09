package com.hc.user.core.service.impl;

import com.hc.common.auth.AuthUserInfo;
import com.hc.common.auth.UserTokenCache;
import com.hc.common.verification.VerificationCodeHandler;
import com.hc.user.core.mapper.UserMapper;
import com.hc.user.core.model.auth.SimpleAuthUserInfo;
import com.hc.common.utils.UUIDUtil;
import com.hc.user.core.oauth.exceptions.SmsInvalidException;
import com.hc.user.core.service.UserAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author a1234
 * @description
 * @create 2024-04-25 16:55
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthServiceImpl.class);

    private static final String LOGIN_CODE_TYPE = "LOGIN";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserTokenCache userTokenCache;
    @Autowired
    private VerificationCodeHandler verificationCodeHandler;
    @Autowired
    private UserMapper userMapper;


    @Override
    public void sendVerificationCode(String phoneNum) {
        // 验证 1 分钟内是否发送过
        LOGGER.info(phoneNum);
        String code = "9527";
        verificationCodeHandler.sendCode(phoneNum, code, LOGIN_CODE_TYPE);

        LOGGER.info("{} 验证码 -->> {}", phoneNum, code);
    }


    @Override
    @Transactional
    public String registerAndLogin(String phoneNum, String verificationCode) {

        // 用户信息注册
        SimpleAuthUserInfo userInfo = userMapper.queryAuthUser(phoneNum);
        if (userInfo == null) {
            registerUser(phoneNum);
        }

        // 执行登录操作
        boolean login = login(phoneNum, verificationCode);
        return "";
    }

    void registerUser(String phoneNum) {
        userMapper.insertUser(UUIDUtil.uuid(), phoneNum);
    }

    @Override
    public boolean login(String phoneNum, String verificationCode) {
        // 校验验证码是否合法
        boolean available = verificationCodeHandler.codeAvailable(phoneNum, verificationCode, LOGIN_CODE_TYPE);
        if (!available) {
            throw new SmsInvalidException();
        }

//        SimpleAuthUserInfo authUserInfo = userMapper.queryAuthUser(phoneNum);
//        String token = UUIDUtil.uuid();
//        userTokenCache.setToken(token, authUserInfo);

        return true;
    }

    @Override
    public boolean smsLogin(String phone, String code) {
        boolean available = verificationCodeHandler.codeAvailable(phone, code, LOGIN_CODE_TYPE);
        if (!available) {
            throw new SmsInvalidException();
        }

        return true;
    }

    @Override
    public String tokenValidate(String token) {
        AuthUserInfo authUserInfo = userTokenCache.tokenUser(token);
        if (authUserInfo == null) {
            return null;
        }

        return token;
    }


}
