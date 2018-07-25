package com.example.blog.controller.blog;

import com.example.blog.domain.RestResponse;
import com.example.blog.domain.blog.ArticleDomain;
import com.example.blog.entity.blog.Article;
import com.example.blog.service.blog.ArticleService;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
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
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, paramType = "query"),
            @ApiImplicitParam(name = "description", value = "文章描述", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "文章内容", required = true, paramType = "query"),
            @ApiImplicitParam(name = "tag", value = "文章标签", dataType = "Set", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "文章作者ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "plateId", value = "文章对应的板块ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "weight", value = "文章权重", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "thumbnail", value = "预览图", paramType = "query")
    })
    @PostMapping("/")
    public RestResponse<Article> add(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "content") String content,
            @RequestParam(name = "tag", required = false) Set<String> tag,
            @RequestParam(name = "userId") long userId,
            @RequestParam(name = "plateId") long plateId,
            @RequestParam(name = "weight", required = false) Integer weight,
            @RequestParam(name = "thumbnail", required = false) String thumbnail
    ) {
        Article article = articleService.addArticle(title, description, content, tag, userId, plateId, weight, thumbnail);
        return RestResponseUtil.success(article, "添加文章成功");
    }

    @ApiOperation(value = "删除文章", notes = "删除文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public RestResponse<Article> delete(@PathVariable(name = "id") long id) {
        articleService.deleteArticle(id);
        return RestResponseUtil.success(null, "删除文章成功");
    }

    @ApiOperation(value = "修改文章", notes = "修改文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, paramType = "query"),
            @ApiImplicitParam(name = "description", value = "文章描述", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "文章内容", required = true, paramType = "query"),
            @ApiImplicitParam(name = "tag", value = "文章标签", dataType = "Set", paramType = "query"),
            @ApiImplicitParam(name = "plateId", value = "文章对应的板块ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "weight", value = "文章权重", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "thumbnail", value = "预览图", paramType = "query")
    })
    @PutMapping("/")
    public RestResponse<Article> edit(
            @RequestParam(name = "id") long id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "content") String content,
            @RequestParam(name = "tag", required = false) Set<String> tag,
            @RequestParam(name = "plateId") long plateId,
            @RequestParam(name = "weight", required = false) Integer weight,
            @RequestParam(name = "thumbnail", required = false) String thumbnail
    ) {
        Article article = articleService.editArticle(id, title, description, content, tag, plateId, weight, thumbnail);
        return RestResponseUtil.success(article, "修改文章成功");
    }

    @ApiOperation(value = "查找文章", notes = "通过各种参数查找文章集合或直接查找所有文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章ID", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "title", value = "文章标题", paramType = "query"),
            @ApiImplicitParam(name = "plateId", value = "板块ID", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "weight", value = "文章最小权重", dataType = "int", paramType = "query")
    })
    @GetMapping("/")
    public RestResponse<Collection<Article>> get(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "plateId", required = false) Long plateId,
            @RequestParam(name = "weight", required = false) Integer weight
    ) {
        Collection<Article> articleCollection;
        if (id != null) {
            articleCollection = new HashSet<>();
            articleCollection.add(articleService.getArticle(id));
        } else if (StringUtils.hasText(title) || plateId != null || weight != null) {
            articleCollection = articleService.getArticles(title, plateId, weight);
        } else {
            articleCollection = articleService.getAllArticles();
        }
        return RestResponseUtil.success(articleCollection);
    }

    @ApiOperation(value = "查找文章", notes = "通过板块ID查找文章集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "plateId", value = "板块ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "pageNumber", value = "页数", defaultValue = "0", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", defaultValue = "8", dataType = "int", paramType = "query")
    })
    @GetMapping("/getByPlateId")
    public RestResponse<Page<ArticleDomain>> getByPlateId(
            @RequestParam(name = "plateId") Long plateId,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "8") Integer pageSize
    ) {
        return RestResponseUtil.success(articleService.getArticlesByPlateId(plateId, pageNumber, pageSize));
    }

}
