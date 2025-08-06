package com.hc.commons.security.beans.token;

import com.hc.commons.security.model.AuthUserInfo;
import com.hc.commons.security.UserAuthCache;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CacheableUserAuthenticationToken extends AbstractAuthenticationToken {

    private final UserAuthCache userAuthCache;
    private final AuthUserInfo jwtUserInfo;

    /**
     * 缓存的 authorities 集合
     */
    private List<GrantedAuthority> cachedAuthorities;

    public CacheableUserAuthenticationToken(AuthUserInfo jwtUserInfo, UserAuthCache userAuthCache) {
        // 初始化一个空的权限集合
        super(Collections.emptyList());
        // 默认是已经认证过的 token，如果这一步设为 false 责会标记当前 token 为 未认证
        setAuthenticated(true);
        this.userAuthCache = userAuthCache;
        this.jwtUserInfo = jwtUserInfo;
    }

    /**
     * 重新 权限
     *
     * @return
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = jwtUserInfo.getAuthorities();
        if (authorities != null) {
            return authorities;
        }
        if (cachedAuthorities == null || cachedAuthorities.isEmpty()) {
            AuthUserInfo cacheUserAuth = userAuthCache.getUserAuth(jwtUserInfo.getUsername());
            if (cacheUserAuth != null && cacheUserAuth.getRoles() != null) {
                cachedAuthorities = cacheUserAuth.getAuthorities();
            }

        }
        return cachedAuthorities;
    }

    @Override
    public Object getCredentials() {
        // 凭证为空
        return null;
    }

    @Override
    public Object getPrincipal() {
        // 身份信息
        return jwtUserInfo.getUsername();
    }
}
