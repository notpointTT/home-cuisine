package com.hc.user.core.oauth.beans;

import com.hc.user.core.model.auth.AuthUserInfo;
import com.hc.user.core.oauth.UserAuthCache;
import io.lettuce.core.dynamic.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author a1234
 * @description
 * @create 2025-07-28 17:52
 */
//@Component
@Deprecated
public class RoleAccessDecisionVoter extends RoleVoter {

    @Autowired
    private UserAuthCache userAuthCache;

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object invocation, Collection<ConfigAttribute> attributes) {

        if (authentication == null || authentication.getPrincipal() == null) {
            return ACCESS_DENIED;
        }

        if (authentication instanceof AnonymousAuthenticationToken) {
            return ACCESS_DENIED;
        }
        // 获取用户名
        String username = authentication.getPrincipal().toString();
        // 从 缓存中 获取用户实际角色
        AuthUserInfo userAuth = userAuthCache.getUserAuth(username);
//        List<String> roles = userAuth.getRoles();
        List<String> roles = Arrays.asList("ADMIN");

        int result = ACCESS_ABSTAIN;

        for (ConfigAttribute attribute : attributes) {
            if (this.supports(attribute)) {
                result = ACCESS_DENIED;

                // Attempt to find a matching granted authority
                for (String role : roles) {
                    if (role.equals(attribute.getAttribute())) {
                        return ACCESS_GRANTED;
                    }
                }
            }
        }

        return result;
    }
}
