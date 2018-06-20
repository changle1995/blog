package com.example.blog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Author: changle
 * Date: 2018/6/18
 * Time: 13:14
 */
@ApiModel(value = "评论表实体类")
@Entity
public class Comment extends BaseEntity {

    private String content;

    @ApiModelProperty(value = "父评论")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="comment_id")
    private Comment comment;

    @ApiModelProperty(value = "评论作者")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
