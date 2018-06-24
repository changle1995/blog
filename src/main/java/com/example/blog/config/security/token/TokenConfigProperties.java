package com.example.blog.config.security.token;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 11:21
 */
@ConfigurationProperties("spring.security.token")
public class TokenConfigProperties {

    private String ignoreRequestUrl;

    private String requiresAuthenticationRequestUrl;

    public String getIgnoreRequestUrl() {
        return ignoreRequestUrl;
    }

    public void setIgnoreRequestUrl(String ignoreRequestUrl) {
        this.ignoreRequestUrl = ignoreRequestUrl;
    }

    public String getRequiresAuthenticationRequestUrl() {
        return requiresAuthenticationRequestUrl;
    }

    public void setRequiresAuthenticationRequestUrl(String requiresAuthenticationRequestUrl) {
        this.requiresAuthenticationRequestUrl = requiresAuthenticationRequestUrl;
    }

}
