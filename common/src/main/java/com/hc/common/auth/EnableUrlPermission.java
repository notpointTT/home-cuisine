package com.hc.common.auth;

import com.hc.common.auth.config.UrlPermissionConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(UrlPermissionConfig.class)
public @interface EnableUrlPermission {
}
