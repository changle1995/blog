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
     * 保存所有权限与url关系的Map
     */
    private Map<String, Collection<String>> authorityToUrlsCollectionMap;

    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * 加载所有权限与url的关系
     * Permission中url值与Authority一一对应
     */
    private void loadResourceDefine() {
        authorityToUrlsCollectionMap = new HashMap<>();
        List<Permission> permissionList = permissionRepository.findAll();
        permissionList.forEach(permission -> {
            Collection<String> urlsCollection = new ArrayList<>();
            urlsCollection.add(permission.getUrl());
            authorityToUrlsCollectionMap.put(permission.getAuthority(), urlsCollection);
        });
    }

    /**
     * 该方法若返回为空则不会调用AccessDecisionManager的decide方法,将被视为认证通过
     * 获取到当前访问的url对应的所有的权限
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 保证所有权限与资源是实时刷新的
        loadResourceDefine();
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        Collection<ConfigAttribute> configAttributeCollection = new ArrayList<>();
        authorityToUrlsCollectionMap.forEach((k, v) -> {
            for (String urls : v) {
                for (String url : urls.split(",")) {
                    if (new AntPathRequestMatcher(url).matches(request)) {
                        configAttributeCollection.add(new SecurityConfig(k));
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
        Collection<ConfigAttribute> configAttributeCollection = new ArrayList<>();
        authorityToUrlsCollectionMap.forEach((k,v) -> configAttributeCollection.add(new SecurityConfig(k)));
        return configAttributeCollection;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

}
