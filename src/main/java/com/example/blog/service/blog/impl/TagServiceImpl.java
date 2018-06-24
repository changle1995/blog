package com.example.blog.service.blog.impl;

import com.example.blog.entity.blog.Tag;
import com.example.blog.service.blog.TagService;
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
public class TagServiceImpl extends BaseServiceImpl<Tag> implements TagService {
}
