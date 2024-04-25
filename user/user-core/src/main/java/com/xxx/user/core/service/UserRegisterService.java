package com.xxx.user.core.service;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author a1234
 * @description
 * @create 2024-04-25 15:37
 */
public interface UserRegisterService {

    /**
     * 获取/发送手机验证码
     * @param phoneNum
     */
    void verificationCode(String phoneNum);

}
