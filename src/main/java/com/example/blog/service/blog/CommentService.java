package com.example.blog.service.blog;

import com.example.blog.domain.blog.CommentDomain;
import com.example.blog.entity.blog.Comment;
import com.example.blog.service.BaseService;
import org.springframework.data.domain.Page;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 17:24
 */
public interface CommentService extends BaseService<Comment> {

    /**
     * 新增评论方法
     *
     * @param articleId 所属文章ID
     * @param commentId 父评论ID
     * @param userId    评论作者ID
     * @param content   评论内容
     * @return 返回新增的评论
     */
    Comment addComment(long articleId, Long commentId, long userId, String content);

    /**
     * 删除评论方法
     *
     * @param id 评论ID
     */
    void deleteComment(long id);

    /**
     * 修改评论方法
     *
     * @param id      评论ID
     * @param content 评论内容
     * @return 返回修改的评论
     */
    Comment editComment(long id, String content);

    /**
     * 根据文章ID查找评论方法
     *
     * @param articleId 文章ID
     * @return 返回该文章下所有评论
     */
    Page<CommentDomain> getCommentDomainsByArticleId(long articleId, Integer pageNumber, Integer pageSize);

    /**
     * 分页查找所有评论方法
     *
     * @return 返回所有评论
     */
    Page<CommentDomain> getCommentDomains(Integer pageNumber, Integer pageSize);

}
