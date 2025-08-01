package com.hc.user.core.auth.provider;


import com.hc.user.auth.model.AuthUserInfo;

public interface AbstractLogin {

    /**
     * 短信验证码登录
     * @param phone
     * @param code
     * @return
     */
    AuthUserInfo smsLogin(String phone, String code);

    /**
     * 用户名密码登录
     * @param username
     * @param password
     * @return
     */
    AuthUserInfo userPasswordLogin(String username, String password);

    /**
     * 微信直接登录
     * @param code
     * @return
     */
    AuthUserInfo wxLogin(String code);

}
