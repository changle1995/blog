package com.example.blog.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

/**
 * 角色分配权限参数对象
 * Author: changle
 * Date: 2018/6/14
 * Time: 11:37
 */
@ApiModel("角色分配权限参数对象")
public class AssignPermissionsRequestBody {

    @JsonProperty(value = "roleId")
    @ApiModelProperty(value = "待分配权限的角色ID")
    private Long roleId;

    @JsonProperty(value = "permissionIdCollection")
    @ApiModelProperty(value = "角色待分配的权限ID集合")
    private Collection<Long> permissionIdCollection;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Collection<Long> getPermissionIdCollection() {
        return permissionIdCollection;
    }

    public void setPermissionIdCollection(Collection<Long> permissionIdCollection) {
        this.permissionIdCollection = permissionIdCollection;
    }

}
