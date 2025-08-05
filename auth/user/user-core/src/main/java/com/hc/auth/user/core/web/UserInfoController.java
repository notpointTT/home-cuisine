package com.hc.auth.user.core.web;

import com.hc.commons.dto.response.ApiResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/info")
public class UserInfoController {

    @GetMapping("/base/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResult<?> baseAdminInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("nickname", "ADMIN");
        return ApiResult.success(map);
    }

    @GetMapping("/base/admin2")
    public ApiResult<?> baseAdminInfo2() {
        Map<String, Object> map = new HashMap<>();
        map.put("nickname", "ADMIN");
        return ApiResult.success(map);
    }

    @GetMapping("/base/user")
    @PreAuthorize("hasRole('USER')")
    public ApiResult<?> baseUserInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("nickname", "USER");
        return ApiResult.success(map);
    }

    @GetMapping("/base")
    public ApiResult<?> baseInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("nickname", "BASE");
        return ApiResult.success(map);
    }

}
