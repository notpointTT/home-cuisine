package com.hc.user.auth.exceptions;

import com.hc.common.emums.ApiResultCode;

public class SmsInvalidException extends AuthException {

    public SmsInvalidException() {
        super(ApiResultCode.SMS_INVALID);
    }

}
