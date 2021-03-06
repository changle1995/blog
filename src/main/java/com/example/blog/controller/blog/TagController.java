package com.example.blog.controller.blog;

import com.example.blog.domain.RestResponse;
import com.example.blog.entity.blog.Tag;
import com.example.blog.service.blog.TagService;
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
 * Time: 18:02
 */
@Api(tags = "标签控制器", description = "标签增删改查接口")
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation(value = "新增标签", notes = "新增标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "标签名称", required = true, paramType = "query")
    })
    @PostMapping("/")
    public RestResponse<Tag> add(@RequestParam String name) {
        Tag tag = tagService.addTag(name);
        return RestResponseUtil.success(tag, "添加标签成功");
    }

    @ApiOperation(value = "删除标签", notes = "删除标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签ID", required = true, dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public RestResponse<Tag> delete(@PathVariable long id) {
        tagService.deleteTag(id);
        return RestResponseUtil.success(null, "删除标签成功");
    }

    @ApiOperation(value = "修改标签", notes = "修改标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "标签名称", required = true, paramType = "query")
    })
    @PutMapping("/")
    public RestResponse<Tag> edit(@RequestParam long id, @RequestParam String name) {
        Tag tag = tagService.editTag(id, name);
        return RestResponseUtil.success(tag, "修改标签成功");
    }

    @ApiOperation(value = "查找标签", notes = "直接查找所有标签")
    @GetMapping("/")
    public RestResponse<Collection<Tag>> get() {
        return RestResponseUtil.success(tagService.getAllTags());
    }

}
