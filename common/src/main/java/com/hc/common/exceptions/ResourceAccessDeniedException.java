package com.hc.common.exceptions;

import com.hc.common.emums.ApiResultCode;

public class ResourceAccessDeniedException extends BaseException{

    public ResourceAccessDeniedException() {
        setCode(ApiResultCode.ACCESS_DENIED);
    }

}
