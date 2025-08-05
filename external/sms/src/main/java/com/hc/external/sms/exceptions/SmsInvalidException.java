package com.hc.external.sms.exceptions;


import com.hc.commons.core.exceptions.BaseException;
import com.hc.commons.dto.emums.ApiResultCode;

public class SmsInvalidException extends BaseException {

    public SmsInvalidException() {
        super(ApiResultCode.SMS_INVALID);
    }

}
