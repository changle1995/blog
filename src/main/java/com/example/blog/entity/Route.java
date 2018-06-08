package com.example.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Set;

/**
 * Author: changle
 * Date: 2018/5/16
 * Time: 15:09
 */
@ApiModel(value = "前端路由表实体类")
@Entity
@Table(name = "route")
public class Route extends BaseEntity {

    @ApiModelProperty(value = "路由名称")
    @Column(name = "name")
    private String name;

    @ApiModelProperty(value = "路由描述")
    @Column(name = "description", length = 1000)
    private String description;

    @ApiModelProperty(value = "路由参数名称")
    @Column(name = "property_name")
    private String propertyName;

    @ApiModelProperty(value = "路由参数值")
    @Column(name = "property_value")
    private String propertyValue;

    @ApiModelProperty(value = "路由级别")
    @Column(name = "level")
    private Integer level;

    @ApiModelProperty(value = "父路由ID")
    @Column(name = "parentId")
    private Long parentId;

    @ApiModelProperty(value = "路由对应的角色")
    @JsonIgnore
    @ManyToMany(mappedBy = "routeSet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

}
