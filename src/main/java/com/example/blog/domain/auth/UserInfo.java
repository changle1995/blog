package com.example.blog.domain.auth;

import com.example.blog.entity.auth.Route;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;
import java.util.Date;

/**
 * 登录成功返回信息类
 * Author: changle
 * Date: 2018/5/12
 * Time: 20:35
 */
@ApiModel("登录成功返回对象类")
public class UserInfo {

    @JsonProperty(value = "user-token")
    @ApiModelProperty(value = "后续请求必须携带的头部token")
    private String token;

    @JsonProperty(value = "id")
    @ApiModelProperty(value = "用户ID")
    private Long id;

    @JsonProperty(value = "username")
    @ApiModelProperty(value = "用户名称")
    private String username;

    @JsonProperty(value = "description")
    @ApiModelProperty(value = "用户描述")
    private String description;

    @JsonProperty(value = "email")
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @JsonProperty(value = "phoneNumber")
    @ApiModelProperty(value = "用户电话")
    private String phoneNumber;

    @JsonProperty(value = "lastLogin")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "最近一次登录时间")
    private Date lastLogin;

    @JsonProperty(value = "routes")
    @ApiModelProperty(value = "用户拥有的前端路由信息")
    private Collection<Route> routeCollection;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Collection<Route> getRouteCollection() {
        return routeCollection;
    }

    public void setRouteCollection(Collection<Route> routeCollection) {
        this.routeCollection = routeCollection;
    }

}
