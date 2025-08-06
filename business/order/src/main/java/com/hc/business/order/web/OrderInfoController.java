package com.hc.business.order.web;

import com.hc.commons.dto.response.ApiResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class OrderInfoController {

    @GetMapping("/base")
    public ApiResult<?> baseInfo() {
        return ApiResult.success("order");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResult<?> adminInfo() {
        return ApiResult.success("admin");
    }

}
