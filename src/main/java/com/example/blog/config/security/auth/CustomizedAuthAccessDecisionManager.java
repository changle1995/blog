package com.example.blog.config.security.auth;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * 自定义权限验证决策器
 * Author: changle
 * Date: 2018/3/24
 * Time: 17:06
 */
@Service("customizedAuthAccessDecisionManager")
public class CustomizedAuthAccessDecisionManager implements AccessDecisionManager {

    /**
     * 每次权限验证将调用此方法
     * 判断用户是否拥有对应的url权限
     * configAttributeCollection中包含所有能访问当前url的权限
     * 从当前Authentication中取所有权限与configAttributeCollection中权限一一对比,若相等则表示通过
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributeCollection) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : configAttributeCollection) {
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                if (grantedAuthority.getAuthority().equals(configAttribute.getAttribute())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("没有权限访问");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

}
