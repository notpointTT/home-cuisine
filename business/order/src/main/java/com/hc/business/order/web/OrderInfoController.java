package com.hc.business.order.web;

import com.hc.business.order.feign.UserFeignClient;
import com.hc.commons.dto.response.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class OrderInfoController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/base")
    public ApiResult<?> baseInfo() {
        return ApiResult.success("order");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResult<?> adminInfo() {
        return ApiResult.success("admin");
    }

    @GetMapping("/user/base")
    public ApiResult<?> baseUserInfo() {
        return userFeignClient.baseUserInfo();
    }

    @GetMapping("/user/admin")
    public ApiResult<?> adminUserInfo() {
        return userFeignClient.baseAdminInfo();
    }

}
