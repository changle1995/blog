package com.example.blog.repository.blog;

import com.example.blog.entity.blog.Article;
import com.example.blog.repository.BaseRepository;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 17:21
 */
public interface ArticleRepository extends BaseRepository<Article, Long> {

    Collection<Article> findAllByTitle(String title);

}
