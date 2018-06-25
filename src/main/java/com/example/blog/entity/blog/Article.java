package com.example.blog.entity.blog;

import com.example.blog.entity.BaseEntity;
import com.example.blog.entity.auth.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

/**
 * Author: changle
 * Date: 2018/3/16
 * Time: 16:09
 */
@ApiModel(value = "文章表实体类")
@Entity
public class Article extends BaseEntity {

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章描述")
    @Column(length = 1000)
    private String description;

    @ApiModelProperty(value = "文章内容")
    @Type(type = "text")
    private String content;

    @ApiModelProperty(value = "文章标签")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "article_tag", joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> tagSet;

    @ApiModelProperty(value = "文章作者")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ApiModelProperty(value = "文章对应的板块")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plate_id")
    private Plate plate;

    @ApiModelProperty(value = "文章阅读数")
    private Integer viewNumber;

    @ApiModelProperty(value = "文章权重")
    private Integer weight;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

}
