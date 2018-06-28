package com.example.blog.service.blog.impl;

import com.example.blog.entity.auth.User;
import com.example.blog.entity.blog.Article;
import com.example.blog.entity.blog.Plate;
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
        return articleRepository.save(generateArticle(title, description, content, tagNameSet, userId, plateId, weight));
    }

    @Override
    public void deleteArticle(long id) {
        articleRepository.delete(id);
    }

    @Override
    public Article editArticle(long id, String title, String description, String content, Set<String> tagNameSet, long plateId, Integer weight) {
        Article article = articleRepository.findOne(id);
        return articleRepository.save(modifyArticle(article, title, description, content, tagNameSet, article.getUser().getId(), plateId, weight));
    }

    @Override
    public Article getArticle(long id) {
        return articleRepository.findOne(id);
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
        return modifyArticle(new Article(), title, description, content, tagNameSet, userId, plateId, weight);
    }

    private Article modifyArticle(Article article, String title, String description, String content, Set<String> tagNameSet, long userId, long plateId, Integer weight) {
        Assert.notNull(article, "该文章不存在");
        Assert.hasText(title, "文章标题不能为空或全空白字符");
        Assert.hasText(content, "文章内容不能为空或全空白字符");
        article.setTitle(title);
        article.setDescription(description);
        article.setContent(content);
        article.setTagSet(tagService.addTagSet(tagNameSet));
        User user = userService.getUser(userId);
        Assert.notNull(user, "作者不存在");
        article.setUser(user);
        Plate plate = plateService.getPlate(plateId);
        Assert.notNull(plate, "板块不存在");
        article.setPlate(plate);
        article.setWeight(weight);
        return article;
    }

}
