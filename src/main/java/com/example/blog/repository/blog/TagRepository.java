package com.example.blog.repository.blog;

import com.example.blog.entity.blog.Tag;
import com.example.blog.repository.BaseRepository;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 17:22
 */
public interface TagRepository extends BaseRepository<Tag, Long> {

    Tag findByName(String name);

}
