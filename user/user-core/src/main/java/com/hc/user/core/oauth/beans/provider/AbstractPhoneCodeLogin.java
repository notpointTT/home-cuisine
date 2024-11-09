package com.hc.user.core.oauth.beans.provider;

public interface AbstractPhoneCodeLogin {

    boolean login(String phone, String code);

}
