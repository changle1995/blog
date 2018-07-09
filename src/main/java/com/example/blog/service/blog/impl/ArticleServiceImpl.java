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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

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
    public Article addArticle(String title, String description, String content, Set<String> tagNameSet, long userId, long plateId, Integer weight, String thumbnail) {
        return articleRepository.save(generateArticle(title, description, content, tagNameSet, userId, plateId, weight, thumbnail));
    }

    @Override
    public void deleteArticle(long id) {
        articleRepository.delete(id);
    }

    @Override
    public Article editArticle(long id, String title, String description, String content, Set<String> tagNameSet, Long plateId, Integer weight, String thumbnail) {
        Article article = articleRepository.findOne(id);
        return articleRepository.save(modifyArticle(article, title, description, content, tagNameSet, article.getUser().getId(), plateId, weight, thumbnail));
    }

    @Override
    public Article getArticle(long id) {
        return articleRepository.findOne(id);
    }

    @Override
    public Collection<Article> getArticles(String title, Long plateId, Integer weight) {
        if (StringUtils.hasText(title)) {
            return articleRepository.findAllByTitle(title, new Sort(Sort.Direction.DESC, "id"));
        } else if (plateId != null) {
            return articleRepository.findAllByPlateId(plateId, new Sort(Sort.Direction.DESC, "id"));
        } else if (weight != null) {
            return articleRepository.findAllByWeightGreaterThanEqual(weight, new Sort(Sort.Direction.DESC, "id"));
        }
        return null;
    }

    @Override
    public Collection<Article> getAllArticles() {
        return articleRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
    }

    private Article generateArticle(String title, String description, String content, Set<String> tagNameSet, long userId, long plateId, Integer weight, String thumbnail) {
        return modifyArticle(new Article(), title, description, content, tagNameSet, userId, plateId, weight, thumbnail);
    }

    private Article modifyArticle(Article article, String title, String description, String content, Set<String> tagNameSet, long userId, Long plateId, Integer weight, String thumbnail) {
        Assert.notNull(article, "该文章不存在");
        Assert.hasText(title, "文章标题不能为空或全空白字符");
        article.setTitle(title);
        article.setDescription(description);
        Assert.hasText(content, "文章内容不能为空或全空白字符");
        article.setContent(content);
        article.setTagSet(tagService.addTagSet(tagNameSet));
        User user = userService.getUser(userId);
        Assert.notNull(user, "作者不存在");
        article.setUser(user);
        if (plateId != null) {
            Plate plate = plateService.getPlate(plateId);
            Assert.notNull(plate, "板块不存在");
            article.setPlate(plate);
        }
        article.setWeight(weight);
        article.setThumbnail(thumbnail);
        return article;
    }

}
