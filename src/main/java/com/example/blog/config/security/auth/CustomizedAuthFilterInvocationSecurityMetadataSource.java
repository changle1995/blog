package com.example.blog.config.security.auth;

import com.example.blog.entity.Permission;
import com.example.blog.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 自定义获取url与权限对应关系的类
 * Author: changle
 * Date: 2018/3/24
 * Time: 14:38
 */
@Service("customizedAuthFilterInvocationSecurityMetadataSource")
public class CustomizedAuthFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /**
     * 保存所有权限的Map,使用name作为key,Permission作为值
     */
    private Map<String, Permission> permissionsMap;

    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * 加载所有权限与url的关系
     * Permission中url值与Authority一一对应
     */
    private void loadResourceDefine() {
        this.permissionsMap = new HashMap<>();
        Collection<Permission> permissionCollection = permissionRepository.findAll();
        permissionCollection.forEach(permission -> this.permissionsMap.put(permission.getAuthority(), permission));
    }

    /**
     * 该方法若返回为空则不会调用AccessDecisionManager的decide方法,将被视为认证通过
     * 获取到当前访问的url对应的所有的权限
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 保证所有权限与资源是实时刷新的
        loadResourceDefine();
        HttpServletRequest httpServletRequest = ((FilterInvocation) o).getHttpRequest();
        Collection<ConfigAttribute> configAttributeCollection = new HashSet<>();
        this.permissionsMap.forEach((authority, permission) -> {
            if (methodMatches(httpServletRequest, permission)) {
                for (String url : permission.getUrl().split(",")) {
                    if (new AntPathRequestMatcher(url).matches(httpServletRequest)) {
                        configAttributeCollection.add(new SecurityConfig(authority));
                        break;
                    }
                }
            }
        });
        return configAttributeCollection;
    }

    /**
     * 获取所有权限
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        loadResourceDefine();
        Collection<ConfigAttribute> configAttributeCollection = new HashSet<>();
        permissionsMap.forEach((authority, permission) -> configAttributeCollection.add(new SecurityConfig(authority)));
        return configAttributeCollection;
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
