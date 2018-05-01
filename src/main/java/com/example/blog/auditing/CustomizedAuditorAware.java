package com.example.blog.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 自动设置新增实体的createPerson与updatePerson
 * Author: changle
 * Date: 2018/3/16
 * Time: 13:58
 */
@Component
public class CustomizedAuditorAware implements AuditorAware<String> {

    /**
     * 每次新增或更新实体的时候会自动调用此方法
     * 根据操作类型返回值将被作为createPerson或updatePerson
     */
    @Override
    public String getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return "nobody";
        }
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }
}
