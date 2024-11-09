package com.hc.user.core.oauth.beans.provider;

public interface AbstractSmsLogin {

    boolean smsLogin(String phone, String code);

}
