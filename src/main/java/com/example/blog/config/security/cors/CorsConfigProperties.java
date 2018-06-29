package com.example.blog.config.security.cors;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 读取cors跨域配置信息
 * Author: changle
 * Date: 2018/5/20
 * Time: 2:27
 */
@ConfigurationProperties("spring.security.cors")
public class CorsConfigProperties {

    private List<String> allowedOrigins;    //访问源地址

    private List<String> allowedMethods;    //访问源请求方法

    private List<String> allowedHeaders;    //访问源请求头

    private Boolean allowCredentials;   //是否可携带验证信息,如cookie

    private Long maxAge;

    private String path;

    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    public List<String> getAllowedMethods() {
        return allowedMethods;
    }

    public void setAllowedMethods(List<String> allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public List<String> getAllowedHeaders() {
        return allowedHeaders;
    }

    public void setAllowedHeaders(List<String> allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }

    public Boolean getAllowCredentials() {
        return allowCredentials;
    }

    public void setAllowCredentials(Boolean allowCredentials) {
        this.allowCredentials = allowCredentials;
    }

    public Long getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Long maxAge) {
        this.maxAge = maxAge;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
