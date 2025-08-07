package com.hc.business.order.feign;

import com.hc.commons.dto.response.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author a1234
 * @description
 * @create 2025-08-07 17:44
 */
@FeignClient("hc-user")
public interface UserFeignClient {
    @GetMapping("/user/info/base/admin")
    ApiResult<?> baseAdminInfo();
    @GetMapping("/user/info/base/user")
    ApiResult<?> baseUserInfo();
}
