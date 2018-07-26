package com.example.blog.domain.auth;

import com.example.blog.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Author: changle
 * Date: 2018/7/26
 * Time: 15:49
 */
@ApiModel(value = "角色映射类")
public class RoleDomain extends BaseDomain {

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String description;

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

}
