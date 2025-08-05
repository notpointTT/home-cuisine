package com.hc.commons.security.exceptions;

import com.hc.commons.core.exceptions.BaseException;
import com.hc.commons.dto.emums.ApiResultCode;

public class UserNotLoginException extends BaseException {

    public UserNotLoginException() {
        super("用户未登录");
        setCode(ApiResultCode.TOKEN_EXPIRED);
    }
}
