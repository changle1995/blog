package com.example.blog.domain.blog;

import com.example.blog.domain.BaseDomain;
import com.example.blog.domain.auth.UserInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Author: changle
 * Date: 2018/7/25
 * Time: 14:33
 */
@ApiModel(value = "评论映射类")
public class CommentDomain extends BaseDomain {

    @ApiModelProperty(value = "所属文章ID")
    private Long articleId;

    @ApiModelProperty(value = "父评论ID")
    private Long commentId;

    @JsonProperty(value = "user")
    @ApiModelProperty(value = "评论作者")
    private UserInfo userInfo;

    @ApiModelProperty(value = "评论内容")
    private String content;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
