package com.hc.user.core.oauth.beans;

import com.hc.user.core.model.auth.AuthUserInfo;
import com.hc.user.core.oauth.UserAuthCache;
import io.lettuce.core.dynamic.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author a1234
 * @description
 * @create 2025-07-28 17:52
 */
@Component
public class RoleAccessDecisionVoter implements AccessDecisionVoter<Object> {

    @Autowired
    private UserAuthCache userAuthCache;

    @Override
    public boolean supports(ConfigAttribute attribute) {
        // 只处理角色校验
        return attribute.getAttribute().startsWith("ROLE_");
    }

    @Override
    public boolean supports(Class<?> clazz) {

        return  // FilterInvocation：Web 请求（URL 访问）
                FilterInvocation.class.isAssignableFrom(clazz) ||
                // MethodInvocation：方法调用（@PreAuthorize）
                MethodInvocation.class.isAssignableFrom(clazz);
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> requiredRoles) {
        // 获取用户名
        String username = authentication.getPrincipal().toString();
        // 从 缓存中 获取用户实际角色
        AuthUserInfo userAuth = userAuthCache.getUserAuth(username);
        List<String> roles = userAuth.getRoles();

        // 2. 检查是否匹配所需角色
        for (ConfigAttribute requiredRole : requiredRoles) {
            if (roles.contains(requiredRole.getAttribute())) {
                // 匹配则放行
                return ACCESS_GRANTED;
            }
        }
        return ACCESS_DENIED;
    }
}
