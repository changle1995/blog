package com.example.blog.service.blog;

import com.example.blog.entity.blog.Article;
import com.example.blog.service.BaseService;

import java.util.Set;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 17:24
 */
public interface ArticleService extends BaseService<Article> {

    /**
     * 新增文章方法
     *
     * @param title       文章名称
     * @param description 文章描述
     * @param content     文章内容
     * @param tagNameSet  文章标签
     * @param userId      文章作者ID
     * @param weight      文章对应的板块ID
     * @return 返回新增的文章
     */
    Article addArticle(String title, String description, String content, Set<String> tagNameSet, long userId, long plateId, Integer weight);

}
