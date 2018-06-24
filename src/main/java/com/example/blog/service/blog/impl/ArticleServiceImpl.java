package com.example.blog.service.blog.impl;

import com.example.blog.entity.blog.Article;
import com.example.blog.repository.blog.ArticleRepository;
import com.example.blog.service.blog.ArticleService;
import com.example.blog.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Set;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 17:27
 */
@Service
@Transactional
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article addArticle(String title, String description, String content, Set<String> tagSet, long userId, long plateId, Integer weight) {
        Article article = generateArticle(title, description, content, tagSet, userId, plateId, weight);
        return articleRepository.save(article);
    }

    private Article generateArticle(String title, String description, String content, Set<String> tagSet, long userId, long plateId, Integer weight) {
        Assert.hasText(title, "文章标题不能为空或全空白字符");
        Assert.hasText(content, "文章内容不能为空或全空白字符");
        Article article = new Article();
        modifyArticle(article, title, description, content, tagSet, userId, plateId, weight);
        return article;
    }

    private void modifyArticle(Article article, String title, String description, String content, Set<String> tagSet, long userId, long plateId, Integer weight) {
        Assert.notNull(article, "文章不能为空");
        if (StringUtils.hasText(title) && !title.equals(article.getTitle())) {
            article.setTitle(title);
        }
        if (StringUtils.hasText(description) && !description.equals(article.getDescription())) {
            article.setDescription(description);
        }
        if (StringUtils.hasText(content) && !content.equals(article.getContent())) {
            article.setContent(content);
        }
        if (!CollectionUtils.isEmpty(tagSet) && !content.equals(article.getContent())) {
            article.setContent(content);
        }
        // todo 修改条件
    }

}
