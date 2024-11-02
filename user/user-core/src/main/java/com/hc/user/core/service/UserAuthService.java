package com.hc.user.core.service;

import com.hc.user.core.model.auth.UsernameLoginModel;

/**
 * @author a1234
 * @description
 * @create 2024-04-25 16:05
 */
public interface UserAuthService {

    /**
     * 获取/发送手机验证码
     * @param phoneNum
     */
    void sendVerificationCode(String phoneNum);

    /**
     * 注册并登录
     * 注册成功后再登录
     * @param phoneNum
     * @param verificationCode
     * @return 登录成功后的 Token
     */
    String registerAndLogin(String phoneNum, String verificationCode);

    /**
     * 登录接口
     * @param phoneNum 手机号码
     * @param verificationCode 验证码
     * @return token
     */
    String login(String phoneNum, String verificationCode);

    String loginUsername(UsernameLoginModel loginModel);

    /**
     * 验证token是否有效
     * @param token
     * @return
     */
    String tokenValidate(String token);

}
