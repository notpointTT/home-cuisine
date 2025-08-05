package com.hc.auth.user.core.service;

import com.hc.auth.user.core.auth.provider.AbstractLogin;

/**
 * @author a1234
 * @description
 * @create 2024-04-25 16:05
 */
public interface UserAuthService extends AbstractLogin {

    /**
     * 获取/发送手机验证码
     * @param phoneNum
     */
    void sendSms(String phoneNum);

    /**
     * 注册
     * @param phoneNum
     * @param smsCode
     * @return 登录成功后的 Token
     */
    String register(String phoneNum, String smsCode);

}
