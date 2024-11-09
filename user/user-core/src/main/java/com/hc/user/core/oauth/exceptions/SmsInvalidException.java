package com.hc.user.core.oauth.exceptions;

import com.hc.common.emums.ApiResultCode;
import org.springframework.security.core.AuthenticationException;

public class SmsInvalidException extends AuthException {

    public SmsInvalidException() {
        super(ApiResultCode.SMS_INVALID);
    }

}
