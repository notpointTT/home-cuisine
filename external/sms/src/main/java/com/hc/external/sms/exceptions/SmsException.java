package com.hc.external.sms.exceptions;

import com.hc.commons.core.exceptions.BaseException;

public class SmsException extends BaseException {

    public SmsException() {
        super("短信发送异常");
    }
}
