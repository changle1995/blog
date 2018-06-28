package com.example.blog.controller.blog;

import com.example.blog.domain.RestResponse;
import com.example.blog.entity.blog.Article;
import com.example.blog.service.blog.ArticleService;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 18:01
 */
@Api(tags = "文章控制器", description = "文章增删改查接口")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "新增文章", notes = "新增文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "文章描述", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "文章内容", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "tagNameSet", value = "文章标签", dataType = "Set<String>", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "文章作者ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "plateId", value = "文章对应的板块ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "weight", value = "文章权重", dataType = "Integer", paramType = "query")
    })
    @PostMapping("/")
    public RestResponse<Article> add(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "content") String content,
            @RequestParam(name = "tag", required = false) Set<String> tagNameSet,
            @RequestParam(name = "userId") long userId,
            @RequestParam(name = "plateId") long plateId,
            @RequestParam(name = "weight", required = false) Integer weight
    ) {
        Article article = articleService.addArticle(title, description, content, tagNameSet, userId, plateId, weight);
        return RestResponseUtil.success(article, "添加文章成功");
    }

    @ApiOperation(value = "删除文章", notes = "删除文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public RestResponse<Article> delete(@PathVariable(name = "id") long id) {
        articleService.deleteArticle(id);
        return RestResponseUtil.success(null, "删除文章成功");
    }

    @ApiOperation(value = "修改文章", notes = "修改文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "title", value = "文章标题", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "文章描述", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "文章内容", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "tagNameSet", value = "文章标签", dataType = "Set<String>", paramType = "query"),
            @ApiImplicitParam(name = "plateId", value = "文章对应的板块ID", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "weight", value = "文章权重", dataType = "Integer", paramType = "query")
    })
    @PutMapping("/")
    public RestResponse<Article> edit(
            @RequestParam(name = "id") long id,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "content", required = false) String content,
            @RequestParam(name = "tag", required = false) Set<String> tagNameSet,
            @RequestParam(name = "plateId", required = false) long plateId,
            @RequestParam(name = "weight", required = false) Integer weight
    ) {
        Article article = articleService.editArticle(id, title, description, content, tagNameSet, plateId, weight);
        return RestResponseUtil.success(article, "修改文章成功");
    }

    @ApiOperation(value = "查找文章", notes = "通过文章标题查找文章集合或直接查找所有文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "文章标题", dataType = "String", paramType = "query")
    })
    @GetMapping("/")
    public RestResponse<Collection<Article>> get(@RequestParam(name = "title", required = false) String title) {
        Collection<Article> articleCollection;
        if (StringUtils.hasText(title)) {
            articleCollection = articleService.getArticles(title);
        } else {
            articleCollection = articleService.getAllArticles();
        }
        return RestResponseUtil.success(articleCollection);
    }

}
