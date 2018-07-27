package com.example.blog.service.blog.impl;

import com.example.blog.domain.blog.CommentDomain;
import com.example.blog.entity.auth.User;
import com.example.blog.entity.blog.Article;
import com.example.blog.entity.blog.Comment;
import com.example.blog.repository.blog.CommentRepository;
import com.example.blog.service.auth.UserService;
import com.example.blog.service.blog.ArticleService;
import com.example.blog.service.blog.CommentService;
import com.example.blog.service.impl.BaseServiceImpl;
import com.example.blog.util.BlogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 17:28
 */
@Service
@Transactional
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Override
    public Comment addComment(long articleId, Long commentId, long userId, String content) {
        return commentRepository.save(generateComment(articleId, commentId, userId, content));
    }

    @Override
    public void deleteComment(long id) {
        commentRepository.delete(id);
    }

    @Override
    public Comment editComment(long id, String content) {
        return commentRepository.save(modifyComment(commentRepository.findOne(id), content));
    }

    @Override
    public Page<CommentDomain> getCommentDomainsByArticleId(long articleId, Integer pageNumber, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNumber, pageSize, new Sort(Sort.Direction.DESC, "id"));
        Page<Comment> commentPage = commentRepository.findAllByArticleId(articleId, pageable);
        return commentPage.map(BlogUtil::getCommentDomainByComment);
    }

    @Override
    public Page<CommentDomain> getCommentDomains(Integer pageNumber, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNumber, pageSize, new Sort(Sort.Direction.DESC, "id"));
        Page<Comment> commentPage = commentRepository.findAll(pageable);
        return commentPage.map(BlogUtil::getCommentDomainByComment);
    }

    private Comment generateComment(long articleId, Long commentId, long userId, String content) {
        Article article = articleService.get(articleId);
        Assert.notNull(article, "该文章不存在");
        Comment parentComment = null;
        if (commentId != null) {
            parentComment = commentRepository.findOne(commentId);
            Assert.notNull(parentComment, "父评论不存在");
        }
        User user = userService.get(userId);
        Assert.notNull(user, "该作者不存在");
        Comment comment = new Comment();
        comment.setArticle(article);
        comment.setComment(parentComment);
        comment.setUser(user);
        return modifyComment(comment, content);
    }

    private Comment modifyComment(Comment comment, String content) {
        Assert.notNull(comment, "该评论不存在");
        Assert.hasText(content, "评论内容不能为空或全空白字符串");
        comment.setContent(content);
        return comment;
    }

}
