package com.example.blog.domain;

import com.example.blog.entity.auth.Route;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

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

    @JsonProperty(value = "username")
    @ApiModelProperty(value = "用户名称")
    private String username;

    @JsonProperty(value = "routes")
    @ApiModelProperty(value = "用户拥有的前端路由信息")
    private Collection<Route> routeCollection;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<Route> getRouteCollection() {
        return routeCollection;
    }

    public void setRouteCollection(Collection<Route> routeCollection) {
        this.routeCollection = routeCollection;
    }

}
