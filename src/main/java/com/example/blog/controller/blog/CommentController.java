package com.example.blog.controller.blog;

import com.example.blog.domain.RestResponse;
import com.example.blog.entity.blog.Comment;
import com.example.blog.service.blog.CommentService;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 18:01
 */
@Api(tags = "评论控制器", description = "评论增删改查接口")
@RestController
@RequestMapping("${controller.blog.comment.root}")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "新增评论", notes = "新增评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "所属文章ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "commentId", value = "父评论ID", dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "评论作者ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "评论内容", required = true, paramType = "query")
    })
    @PostMapping("${controller.blog.comment.add}")
    public RestResponse<Comment> add(
            @RequestParam(name = "articleId") long articleId,
            @RequestParam(name = "commentId", required = false) Long commentId,
            @RequestParam(name = "userId") long userId,
            @RequestParam(name = "content") String content
    ) {
        Comment comment = commentService.addComment(articleId, commentId, userId, content);
        return RestResponseUtil.success(comment, "添加评论成功");
    }

    @ApiOperation(value = "删除评论", notes = "删除评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "评论ID", required = true, dataType = "long", paramType = "path")
    })
    @DeleteMapping("${controller.blog.comment.delete}")
    public RestResponse<Comment> delete(@PathVariable(name = "id") long id) {
        commentService.deleteComment(id);
        return RestResponseUtil.success(null, "删除评论成功");
    }

    @ApiOperation(value = "修改评论", notes = "修改评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "评论ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "评论内容", required = true, paramType = "query")
    })
    @PutMapping("${controller.blog.comment.edit}")
    public RestResponse<Comment> edit(@RequestParam(name = "id") long id, @RequestParam(name = "content") String content) {
        Comment comment = commentService.editComment(id, content);
        return RestResponseUtil.success(comment, "修改评论成功");
    }

    @ApiOperation(value = "查找评论", notes = "通过评论所属文章ID查找评论集合或直接查找所有评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "所属文章ID", dataType = "long", paramType = "query")
    })
    @GetMapping("${controller.blog.comment.get}")
    public RestResponse<Collection<Comment>> get(@RequestParam(name = "articleId", required = false) Long articleId) {
        Collection<Comment> commentCollection;
        if (articleId != null) {
            commentCollection = commentService.getComments(articleId);
        } else {
            commentCollection = commentService.getAllComments();
        }
        return RestResponseUtil.success(commentCollection);
    }

}
