package com.example.blog.entity.blog;

import com.example.blog.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Author: changle
 * Date: 2018/3/15
 * Time: 22:27
 */
@ApiModel(value = "板块表实体类")
@Entity
public class Plate extends BaseEntity {

    @ApiModelProperty(value = "板块名称")
    private String name;

    @ApiModelProperty(value = "板块描述")
    @Column(length = 1000)
    private String description;

    @ApiModelProperty(value = "板块状态")
    private Integer state;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}
