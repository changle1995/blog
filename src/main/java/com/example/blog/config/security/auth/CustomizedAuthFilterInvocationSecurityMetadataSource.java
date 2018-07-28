package com.example.blog.config.security.auth;

import com.example.blog.entity.auth.Permission;
import com.example.blog.entity.auth.Role;
import com.example.blog.repository.auth.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * 自定义获取url与权限对应关系的类
 * Author: changle
 * Date: 2018/3/24
 * Time: 14:38
 */
@Service("customizedAuthFilterInvocationSecurityMetadataSource")
public class CustomizedAuthFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /**
     * 保存所有角色
     */
    private Collection<Role> roleCollection;

    @Autowired
    private RoleRepository roleRepository;

    /**
     * 加载所有角色
     */
    private void loadResourceDefine() {
        this.roleCollection = roleRepository.findAll();
    }

    /**
     * 该方法若返回为空则不会调用AccessDecisionManager的decide方法,将被视为认证通过
     * 获取到当前访问的url对应的所有的角色
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 保证所有角色与资源是实时刷新的
        loadResourceDefine();
        HttpServletRequest httpServletRequest = ((FilterInvocation) o).getHttpRequest();
        Collection<ConfigAttribute> configAttributeCollection = new HashSet<>();
        this.roleCollection.forEach(role -> role.getPermissionSet().forEach(permission -> {
            if (methodMatches(httpServletRequest, permission)) {
                if (new AntPathRequestMatcher(permission.getUrl()).matches(httpServletRequest)) {
                    configAttributeCollection.add(new SecurityConfig(role.getName()));
                }
            }
        }));
        return configAttributeCollection;
    }

    /**
     * 获取所有角色
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        loadResourceDefine();
        return this.roleCollection.stream().map(role -> new SecurityConfig(role.getName())).collect(Collectors.toSet());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

    private boolean methodMatches(HttpServletRequest httpServletRequest, Permission permission) {
        if (!StringUtils.hasText(permission.getMethod()) || permission.getMethod().equals("*")) {
            return true;
        }
        String[] methods = permission.getMethod().split(",");
        for (String method : methods) {
            method = method.replaceAll("\\s", "").toUpperCase();
            if (httpServletRequest.getMethod().equals(method)) {
                return true;
            }
        }
        return false;
    }

}
