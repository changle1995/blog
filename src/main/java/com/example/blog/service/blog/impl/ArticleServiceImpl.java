package com.example.blog.service.blog.impl;

import com.example.blog.entity.blog.Article;
import com.example.blog.repository.blog.ArticleRepository;
import com.example.blog.service.auth.UserService;
import com.example.blog.service.blog.ArticleService;
import com.example.blog.service.blog.PlateService;
import com.example.blog.service.blog.TagService;
import com.example.blog.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
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

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    @Autowired
    private PlateService plateService;

    @Override
    public Article addArticle(String title, String description, String content, Set<String> tagNameSet, long userId, long plateId, Integer weight) {
        Article article = generateArticle(title, description, content, tagNameSet, userId, plateId, weight);
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(long id) {
        Article article = articleRepository.findOne(id);
        Assert.notNull(article, "该文章不存在");
        articleRepository.delete(article);
    }

    @Override
    public Article editArticle(long id, String title, String description, String content, Set<String> tagNameSet, long plateId, Integer weight) {
        Article article = articleRepository.findOne(id);
        Assert.notNull(article, "该文章不存在");
        modifyArticle(article, title, description, content, tagNameSet, article.getUser().getId(), plateId, weight);
        return articleRepository.save(article);
    }

    @Override
    public Article getArticle(long id) {
        Article article = articleRepository.findOne(id);
        Assert.notNull(article, "该文章不存在");
        return article;
    }

    @Override
    public Collection<Article> getArticles(String title) {
        return articleRepository.findAllByTitle(title);
    }

    @Override
    public Collection<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    private Article generateArticle(String title, String description, String content, Set<String> tagNameSet, long userId, long plateId, Integer weight) {
        Article article = new Article();
        modifyArticle(article, title, description, content, tagNameSet, userId, plateId, weight);
        return article;
    }

    private void modifyArticle(Article article, String title, String description, String content, Set<String> tagNameSet, long userId, long plateId, Integer weight) {
        Assert.notNull(article, "文章不能为空");
        Assert.hasText(title, "文章标题不能为空或全空白字符");
        Assert.hasText(content, "文章内容不能为空或全空白字符");
        article.setTitle(title);
        article.setDescription(description);
        article.setContent(content);
        article.setTagSet(tagService.addTagSet(tagNameSet));
        article.setUser(userService.getUser(userId));
        article.setPlate(plateService.getPlate(plateId));
        article.setWeight(weight);
    }

}
