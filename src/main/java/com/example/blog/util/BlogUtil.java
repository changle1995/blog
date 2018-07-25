package com.example.blog.util;

import com.example.blog.domain.blog.ArticleDomain;
import com.example.blog.domain.blog.CommentDomain;
import com.example.blog.entity.auth.Route;
import com.example.blog.entity.auth.User;
import com.example.blog.entity.blog.Article;
import com.example.blog.entity.blog.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

/**
 * 博客相关工具类
 * Author: changle
 * Date: 2018/7/25
 * Time: 14:23
 */
public class BlogUtil {

    /**
     * 获取用户下所有的路由
     *
     * @param user 用户
     * @return 返回用户下所有的路由
     */
    public static Collection<Route> getRouteCollectionByUser(User user) {
        Assert.notNull(user, "用户不能为空");
        Collection<Route> routeCollection = new HashSet<>();
        Optional.ofNullable(user.getRoleSet())
                .orElse(new HashSet<>())
                .forEach(role -> routeCollection.addAll(role.getRouteSet()));
        return routeCollection;
    }

    /**
     * 提取文章信息包装为ArticleDomain
     *
     * @param article 文章
     * @return 返回文章对应的ArticleDomain
     */
    public static ArticleDomain getArticleDomainByArticle(Article article) {
        Assert.notNull(article, "文章不能为空");
        ArticleDomain articleDomain = new ArticleDomain();
        articleDomain.setUserInfo(AuthUtil.getUserInfoByUserAndToken(article.getUser(), null));
        BeanUtils.copyProperties(article, articleDomain);
        return articleDomain;
    }

    /**
     * 提取评论信息包装为CommentDomain
     *
     * @param comment 评论
     * @return 返回评论对应的CommentDomain
     */
    public static CommentDomain getCommentDomainByComment(Comment comment) {
        Assert.notNull(comment, "评论不能为空");
        CommentDomain commentDomain = new CommentDomain();
        commentDomain.setArticleId(comment.getArticle().getId());
        commentDomain.setCommentId(comment.getComment() == null ? null : comment.getComment().getId());
        commentDomain.setUserInfo(AuthUtil.getUserInfoByUserAndToken(comment.getUser(), null));
        BeanUtils.copyProperties(comment, commentDomain);
        return commentDomain;
    }

}
