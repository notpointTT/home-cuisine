package com.hc.business.order.web;

import com.hc.commons.dto.response.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/openx")
public class OpenXController {
    @GetMapping("/")
    public ApiResult<?> baseInfo() {
        return ApiResult.success("openx");
    }

}
