package com.example.blog.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: changle
 * Date: 2018/6/29
 * Time: 14:00
 */
@Component
@ConfigurationProperties("spring.security")
public class SecurityConfigProperties {

    private String successForwardUrl;

    private String failureForwardUrl;

    private String logoutSuccessUrl;

    private String authenticationEntryPointUrl;

    private String accessDeniedUrl;

    private int maximumSessions;

    private boolean maxSessionsPreventsLogin;

    public String getSuccessForwardUrl() {
        return successForwardUrl;
    }

    public void setSuccessForwardUrl(String successForwardUrl) {
        this.successForwardUrl = successForwardUrl;
    }

    public String getFailureForwardUrl() {
        return failureForwardUrl;
    }

    public void setFailureForwardUrl(String failureForwardUrl) {
        this.failureForwardUrl = failureForwardUrl;
    }

    public String getLogoutSuccessUrl() {
        return logoutSuccessUrl;
    }

    public void setLogoutSuccessUrl(String logoutSuccessUrl) {
        this.logoutSuccessUrl = logoutSuccessUrl;
    }

    public String getAuthenticationEntryPointUrl() {
        return authenticationEntryPointUrl;
    }

    public void setAuthenticationEntryPointUrl(String authenticationEntryPointUrl) {
        this.authenticationEntryPointUrl = authenticationEntryPointUrl;
    }

    public String getAccessDeniedUrl() {
        return accessDeniedUrl;
    }

    public void setAccessDeniedUrl(String accessDeniedUrl) {
        this.accessDeniedUrl = accessDeniedUrl;
    }

    public int getMaximumSessions() {
        return maximumSessions;
    }

    public void setMaximumSessions(int maximumSessions) {
        this.maximumSessions = maximumSessions;
    }

    public boolean isMaxSessionsPreventsLogin() {
        return maxSessionsPreventsLogin;
    }

    public void setMaxSessionsPreventsLogin(boolean maxSessionsPreventsLogin) {
        this.maxSessionsPreventsLogin = maxSessionsPreventsLogin;
    }
}
