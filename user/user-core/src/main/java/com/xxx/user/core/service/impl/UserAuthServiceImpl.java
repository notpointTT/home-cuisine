package com.xxx.user.core.service.impl;

import com.xxx.user.core.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author a1234
 * @description
 * @create 2024-04-25 16:55
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Override
    public void sendVerificationCode(String phoneNum) {
        // 验证 1 分钟内是否发送过

        // Redis 缓存验证码，缓存时间 = 验证过期时间 + 30S

        // 发送验证码
    }

    @Override
    public String registerAndLogin(String phoneNum, String verificationCode) {

        // 验证 verificationCode 是否合法

        // 用户信息注册

        // 执行登录操作
        return login(phoneNum, verificationCode);
    }

    @Override
    public String login(String phoneNum, String verificationCode) {
        return null;
    }

    @Override
    public String tokenValidate(String token) {
        return null;
    }
}
