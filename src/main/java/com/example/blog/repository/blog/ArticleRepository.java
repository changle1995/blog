package com.example.blog.repository.blog;

import com.example.blog.entity.blog.Article;
import com.example.blog.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 17:21
 */
public interface ArticleRepository extends BaseRepository<Article, Long> {

    Page<Article> findAllByPlateId(Long plateId, Pageable pageable);

    Page<Article> findAllByWeightGreaterThanEqual(Integer weight, Pageable pageable);

}
