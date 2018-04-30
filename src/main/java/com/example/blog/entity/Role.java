package com.example.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/20
 * Time: 23:05
 */
@ApiModel(value = "角色表实体类")
@Entity(name = "role")
public class Role extends BaseEntity {

    @ApiModelProperty(value = "角色名称")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "角色描述")
    @Column(name = "description", length = 1000)
    private String description;

    @ApiModelProperty(value = "角色对应的用户")
    @JsonIgnore
    @ManyToMany(mappedBy = "roleList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> userList;

    @ApiModelProperty(value = "角色对应的权限")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    private List<Permission> permissionList;

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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
