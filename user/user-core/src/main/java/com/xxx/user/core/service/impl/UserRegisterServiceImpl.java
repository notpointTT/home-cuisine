package com.xxx.user.core.service.impl;

import com.xxx.user.core.service.UserRegisterService;
import org.springframework.stereotype.Service;

/**
 * @author a1234
 * @description
 * @create 2024-04-25 15:37
 */
@Service
public class UserRegisterServiceImpl implements UserRegisterService {
    @Override
    public void verificationCode(String phoneNum) {
        // 验证 1 分钟内是否发送过

        // Redis 缓存验证码，缓存时间 = 验证过期时间 + 30S

        // 发送验证码
    }
}
