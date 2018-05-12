package com.example.blog.domain;

import com.example.blog.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录成功返回信息类
 * Author: changle
 * Date: 2018/5/12
 * Time: 20:35
 */
@ApiModel("登录成功返回对象类")
public class UserInfo {

    @ApiModelProperty(value = "后续请求必须携带的头部token")
    private String token;

    @ApiModelProperty(value = "用户实体信息类")
    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
