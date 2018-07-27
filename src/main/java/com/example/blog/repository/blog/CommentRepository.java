package com.example.blog.repository.blog;

import com.example.blog.entity.blog.Comment;
import com.example.blog.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 17:22
 */
public interface CommentRepository extends BaseRepository<Comment, Long> {

    Page<Comment> findAllByArticleId(long articleId, Pageable pageable);

}
