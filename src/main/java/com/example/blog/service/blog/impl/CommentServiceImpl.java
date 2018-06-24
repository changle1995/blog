package com.example.blog.service.blog.impl;

import com.example.blog.entity.blog.Comment;
import com.example.blog.service.blog.CommentService;
import com.example.blog.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 17:28
 */
@Service
@Transactional
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {
}
