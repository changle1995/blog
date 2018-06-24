package com.example.blog.controller.blog;

import com.example.blog.domain.RestResponse;
import com.example.blog.entity.blog.Article;
import com.example.blog.service.blog.ArticleService;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @ApiImplicitParam(name = "tagSet", value = "文章标签", dataType = "Set<String>", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "文章作者ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "plateId", value = "文章对应的板块ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "weight", value = "文章权重", dataType = "Integer", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功")
    })
    @PostMapping("/")
    public RestResponse<Article> add(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "content") String content,
            @RequestParam(name = "tag", required = false) Set<String> tagSet,
            @RequestParam(name = "userId") long userId,
            @RequestParam(name = "plateId") long plateId,
            @RequestParam(name = "weight", required = false) Integer weight
    ) {
        Article article = articleService.addArticle(title, description, content, tagSet, userId, plateId, weight);
        return RestResponseUtil.success(article, "添加文章成功");
    }

    // todo 其余

}
