package com.example.blog.config.security.token;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 11:21
 */
@ConfigurationProperties("spring.security.token")
public class TokenConfigProperties {

    private List<String> ignoreAuthenticationRequestUrls;

    private String requiresAuthenticationRequestUrl;

    private String writerRequestUrl;

    public List<String> getIgnoreAuthenticationRequestUrls() {
        return ignoreAuthenticationRequestUrls;
    }

    public void setIgnoreAuthenticationRequestUrls(List<String> ignoreAuthenticationRequestUrls) {
        this.ignoreAuthenticationRequestUrls = ignoreAuthenticationRequestUrls;
    }

    public String getRequiresAuthenticationRequestUrl() {
        return requiresAuthenticationRequestUrl;
    }

    public void setRequiresAuthenticationRequestUrl(String requiresAuthenticationRequestUrl) {
        this.requiresAuthenticationRequestUrl = requiresAuthenticationRequestUrl;
    }

    public String getWriterRequestUrl() {
        return writerRequestUrl;
    }

    public void setWriterRequestUrl(String writerRequestUrl) {
        this.writerRequestUrl = writerRequestUrl;
    }

}
