package com.hc.user.core.service.impl;

import com.hc.common.utils.DesUtil;
import com.hc.user.core.entities.UserEntity;
import com.hc.user.core.oauth.UserAuthCache;
import com.hc.common.sms.SmsSender;
import com.hc.common.verification.VerificationCodeHandler;
import com.hc.user.core.mapper.UserMapper;
import com.hc.user.core.model.auth.AuthUserInfo;
import com.hc.common.utils.UUIDUtil;
import com.hc.user.core.oauth.exceptions.LoginFailException;
import com.hc.user.core.oauth.exceptions.SmsInvalidException;
import com.hc.user.core.service.UserAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.hc.common.constant.CommonConstant.SMS_CODE_LENGTH;

/**
 * @author a1234
 * @description
 * @create 2024-04-25 16:55
 */
@Service
@RefreshScope
public class UserAuthServiceImpl implements UserAuthService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthServiceImpl.class);

    private static final String LOGIN_CODE_TYPE = "LOGIN";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserAuthCache userAuthCache;
    @Autowired
    private VerificationCodeHandler verificationCodeHandler;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SmsSender smsSender;


    @Override
    public void sendSms(String phoneNum) {
        // 验证 1 分钟内是否发送过
        LOGGER.info(phoneNum);
        // 随机生成
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, SMS_CODE_LENGTH - 1)));
        verificationCodeHandler.sendCode(phoneNum, code, LOGIN_CODE_TYPE);

        LOGGER.info("{} 验证码 -->> {}", phoneNum, code);
    }


    @Override
    @Transactional
    public String register(String phoneNum, String smsCode) {

        // 用户信息注册
        UserEntity userEntity = userMapper.queryByPhoneNum(phoneNum);
        if (userEntity == null) {
            registerUser(phoneNum);
        }

        return "";
    }

    void registerUser(String phoneNum) {
        userMapper.insertUser(UUIDUtil.uuid(), phoneNum);
    }


    @Override
    public AuthUserInfo smsLogin(String phone, String code) {
        boolean available = verificationCodeHandler.codeAvailable(phone, code, LOGIN_CODE_TYPE);
        if (!available) {
            throw new SmsInvalidException();
        }

        UserEntity userEntity = userMapper.queryByPhoneNum(phone);
        return user2Auth(userEntity);
    }

    @Override
    public AuthUserInfo userPasswordLogin(String username, String password) {
        UserEntity userEntity = userMapper.queryByUsername(username);
        String passwordHash = userEntity.getPasswordHash();
        if (!Objects.equals(DesUtil.desEncrypt(password), passwordHash)) {
            throw new LoginFailException();
        }

        return user2Auth(userEntity);
    }

    @Override
    public AuthUserInfo wxLogin(String code) {
        return null;
    }

    List<String> userRoles(long userId) {
        // todo 根据用户id，返回用户角色清单
        return Arrays.asList("USER");
    }

    AuthUserInfo user2Auth(UserEntity userEntity) {
        AuthUserInfo userInfo = new AuthUserInfo();
        userInfo.setUsername(userEntity.getUsername());
        userInfo.setPhoneNum(userEntity.getPhoneNum());
        userInfo.setRoles(userRoles(userEntity.getId()));

        return userInfo;
    }

}
