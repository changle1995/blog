package com.example.blog.entity.blog;

import com.example.blog.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * Author: changle
 * Date: 2018/6/22
 * Time: 17:13
 */
@ApiModel(value = "标签表实体类")
@Entity
public class Tag extends BaseEntity {

    @ApiModelProperty(value = "标签名称")
    private String name;

    @ApiModelProperty(value = "标签对应的用户")
    @JsonIgnore
    @ManyToMany(mappedBy = "tagSet", cascade = CascadeType.ALL)
    private Set<Article> articleSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Article> getArticleSet() {
        return articleSet;
    }

    public void setArticleSet(Set<Article> articleSet) {
        this.articleSet = articleSet;
    }

}
