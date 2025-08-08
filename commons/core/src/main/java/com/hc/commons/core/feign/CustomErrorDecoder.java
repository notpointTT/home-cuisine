package com.hc.commons.core.feign;

import com.hc.commons.core.exceptions.*;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String requestUrl = response.request().url();
        int status = response.status();

        String message = String.format("Feign调用失败，URL： %s，状态码: %d, 方法: %s", requestUrl, status, methodKey);
        log.error(message);

        switch (status) {
            case 400:
                return new BadRequestException();
            case 401:
                return new UnauthorizedException();
            case 404:
                return new ResourceNotFoundException();
            case 429:
                return new TooManyRequestException();
            case 500:
                return new InternalServerErrorException();
            case 503:
                return new ServiceUnavailableException();
            default:
                return new ServiceUnavailableException();
        }
    }
}