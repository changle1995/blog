package com.example.blog.domain.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Collection;

/**
 * 用户分配角色参数对象
 * Author: changle
 * Date: 2018/6/14
 * Time: 11:22
 */
@ApiModel("用户分配角色参数对象")
public class AssignRolesRequestBody {

    @JsonProperty(value = "userId")
    @ApiModelProperty(value = "待分配角色的用户ID")
    private Long userId;

    @JsonProperty(value = "roleIdCollection")
    @ApiModelProperty(value = "用户待分配的角色ID集合")
    private Collection<Long> roleIdCollection;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Collection<Long> getRoleIdCollection() {
        return roleIdCollection;
    }

    public void setRoleIdCollection(Collection<Long> roleIdCollection) {
        this.roleIdCollection = roleIdCollection;
    }

}
