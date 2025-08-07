package com.hc.commons.security.feign;

import com.hc.commons.core.exceptions.BaseException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import java.io.Reader;

//@Component
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        if (response.status() == 404) {
//            return new ResourceNotFoundException("用户不存在");
        }

        return new BaseException("Feign 调用异常");
    }
}
