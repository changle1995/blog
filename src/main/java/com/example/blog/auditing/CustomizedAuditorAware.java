package com.example.blog.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * Author: changle
 * Date: 2018/3/16
 * Time: 13:58
 */
@Component
public class CustomizedAuditorAware implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        return "changle";
    }
}
