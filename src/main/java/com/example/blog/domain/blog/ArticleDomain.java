package com.example.blog.domain.blog;

import com.example.blog.domain.BaseDomain;
import com.example.blog.domain.auth.UserInfo;
import com.example.blog.entity.blog.Plate;
import com.example.blog.entity.blog.Tag;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

/**
 * Author: changle
 * Date: 2018/7/25
 * Time: 10:21
 */
@ApiModel(value = "文章映射类")
public class ArticleDomain extends BaseDomain {

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章描述")
    private String description;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "文章标签")
    private Set<Tag> tagSet;

    @JsonProperty(value = "user")
    @ApiModelProperty(value = "文章作者")
    private UserInfo userInfo;

    @ApiModelProperty(value = "文章对应的板块")
    private Plate plate;

    @ApiModelProperty(value = "文章阅读数")
    private Integer viewNumber;

    @ApiModelProperty(value = "文章权重")
    private Integer weight;

    @ApiModelProperty(value = "预览图")
    private String thumbnail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Tag> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Plate getPlate() {
        return plate;
    }

    public void setPlate(Plate plate) {
        this.plate = plate;
    }

    public Integer getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

}
