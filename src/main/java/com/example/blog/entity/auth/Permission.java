package com.example.blog.entity.auth;

import com.example.blog.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * Author: changle
 * Date: 2018/3/22
 * Time: 0:47
 */
@ApiModel(value = "后端权限表实体类")
@Entity
public class Permission extends BaseEntity {

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限描述")
    @Column(length = 1000)
    private String description;

    @ApiModelProperty(value = "权限对应url路径")
    @Column(length = 1000)
    private String url;

    @ApiModelProperty(value = "权限对应url路径的方法")
    private String method;

    @ApiModelProperty(value = "权限对应的用户")
    @JsonIgnore
    @ManyToMany(mappedBy = "permissionSet", cascade = CascadeType.ALL)
    private Set<Role> roleSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

}
