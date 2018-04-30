package com.example.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/22
 * Time: 0:47
 */
@ApiModel(value = "权限表实体类")
@Entity
@Table(name = "permission")
public class Permission extends BaseEntity implements GrantedAuthority {

    @ApiModelProperty(value = "权限名称")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "权限描述")
    @Column(name = "description", length = 1000)
    private String description;

    @ApiModelProperty(value = "权限对应url路径")
    @Column(name = "url")
    private String url;

    @ApiModelProperty(value = "权限对应的用户")
    @JsonIgnore
    @ManyToMany(mappedBy = "permissionList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Role> roleList;

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

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return name;
    }
}
