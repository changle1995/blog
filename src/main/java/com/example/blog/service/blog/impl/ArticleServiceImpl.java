package com.example.blog.service.blog.impl;

import com.example.blog.entity.blog.Article;
import com.example.blog.repository.auth.UserRepository;
import com.example.blog.repository.blog.ArticleRepository;
import com.example.blog.repository.blog.PlateRepository;
import com.example.blog.repository.blog.TagRepository;
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

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlateRepository plateRepository;

    @Override
    public Article addArticle(String title, String description, String content, Set<String> tagNameSet, long userId, long plateId, Integer weight) {
        Article article = generateArticle(title, description, content, tagNameSet, userId, plateId, weight);
        return articleRepository.save(article);
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
//        article.setTagSet(tagRepository.findAllByNameIn(tagNameSet));
//        article.setTagSet(tagSet);
//        article.setUser(userId);
//        article.setPlate(plateId);
        article.setWeight(weight);
        // todo
    }

}
