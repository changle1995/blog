package com.example.blog.repository.blog;

import com.example.blog.entity.blog.Comment;
import com.example.blog.repository.BaseRepository;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 17:22
 */
public interface CommentRepository extends BaseRepository<Comment, Long> {

    Collection<Comment> findAllByArticleId(long articleId);

}
