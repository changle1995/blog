package com.example.blog.service.blog;

import com.example.blog.domain.blog.ArticleDomain;
import com.example.blog.entity.blog.Article;
import com.example.blog.service.BaseService;
import org.springframework.data.domain.Page;

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
     * @param title       文章标题
     * @param description 文章描述
     * @param content     文章内容
     * @param tagNameSet  文章标签
     * @param userId      文章作者ID
     * @param plateId     文章对应的板块ID
     * @param weight      文章权重
     * @param thumbnail   预览图
     * @return 返回新增的文章
     */
    Article addArticle(String title, String description, String content, Set<String> tagNameSet, long userId, long plateId, Integer weight, String thumbnail);

    /**
     * 删除文章方法
     *
     * @param id 文章ID
     */
    void deleteArticle(long id);

    /**
     * 修改文章方法
     *
     * @param id          文章ID
     * @param title       文章标题
     * @param description 文章描述
     * @param content     文章内容
     * @param tagNameSet  文章标签
     * @param plateId     文章对应的板块ID
     * @param weight      文章权重
     * @param thumbnail   预览图
     * @return 返回修改后的文章
     */
    Article editArticle(long id, String title, String description, String content, Set<String> tagNameSet, Long plateId, Integer weight, String thumbnail);

    /**
     * 分页根据板块ID查找文章方法
     *
     * @param plateId    文章对应的板块ID
     * @param pageNumber 页数
     * @param pageSize   每页数量
     * @return 返回获取的文章对应的ArticleDomain
     */
    Page<ArticleDomain> getArticleDomainsByPlateId(Long plateId, Integer pageNumber, Integer pageSize);

    /**
     * 分页根据最小文章权重查找文章方法
     *
     * @param weight    最小文章权重
     * @param pageNumber 页数
     * @param pageSize   每页数量
     * @return 返回获取的文章对应的ArticleDomain
     */
    Page<ArticleDomain> getArticleDomainsByWeight(Integer weight, Integer pageNumber, Integer pageSize);

    /**
     * 分页查找文章集合
     *
     * @return 返回文章集合对应的ArticleDomain
     */
    Page<ArticleDomain> getArticleDomains(Integer pageNumber, Integer pageSize);

    /**
     * 根据文章ID查找ArticleDomain方法
     *
     * @param id 文章ID
     * @return 返回获取的文章对应的ArticleDomain
     */
    ArticleDomain getArticleDomain(long id);

}
