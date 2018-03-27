package com.example.blog.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Author: changle
 * Date: 2018/3/13
 * Time: 7:17
 */
@ConfigurationProperties("spring.datasource")
public class DruidDBConfigProperties {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private String filters;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }
}
