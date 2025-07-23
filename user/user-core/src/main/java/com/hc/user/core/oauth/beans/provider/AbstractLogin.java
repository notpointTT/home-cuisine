package com.hc.user.core.oauth.beans.provider;

import com.hc.user.core.model.auth.SimpleAuthUserInfo;
import org.springframework.security.core.userdetails.UserDetails;

public interface AbstractLogin {

    /**
     * 短信验证码登录
     * @param phone
     * @param code
     * @return
     */
    SimpleAuthUserInfo smsLogin(String phone, String code);

    /**
     * 用户名密码登录
     * @param username
     * @param password
     * @return
     */
    SimpleAuthUserInfo upLogin(String username, String password);

    /**
     * 微信直接登录
     * @param code
     * @return
     */
    SimpleAuthUserInfo wxLogin(String code);

}
