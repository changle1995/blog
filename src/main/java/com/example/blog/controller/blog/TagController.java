package com.example.blog.controller.blog;

import com.example.blog.domain.RestResponse;
import com.example.blog.entity.blog.Tag;
import com.example.blog.service.blog.TagService;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;

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
            @ApiImplicitParam(name = "name", value = "标签名称", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功")
    })
    @PostMapping("/")
    public RestResponse<Tag> add(@RequestParam(name = "name") String name) {
        Tag tag = tagService.addTag(name);
        return RestResponseUtil.success(tag, "添加标签成功");
    }

    @ApiOperation(value = "删除标签", notes = "删除标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签ID", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功")
    })
    @DeleteMapping("/{id}")
    public RestResponse<Tag> delete(@PathVariable(name = "id") long id) {
        tagService.deleteTag(id);
        return RestResponseUtil.success(null, "删除标签成功");
    }

    @ApiOperation(value = "修改标签", notes = "修改标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "标签名称", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功")
    })
    @PutMapping("/")
    public RestResponse<Tag> edit(@RequestParam(name = "id") long id, @RequestParam(name = "name") String name) {
        Tag tag = tagService.editTag(id, name);
        return RestResponseUtil.success(tag, "修改标签成功");
    }

    @ApiOperation(value = "查找标签", notes = "通过标签名称查找标签或直接查找所有标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "标签名称", dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查找成功")
    })
    @GetMapping("/")
    public RestResponse<Collection<Tag>> get(@RequestParam(name = "name", required = false) String name) {
        Collection<Tag> tagCollection;
        if (StringUtils.hasText(name)) {
            tagCollection = new HashSet<>();
            Tag tag = tagService.getTag(name);
            tagCollection.add(tag);
        } else {
            tagCollection = tagService.getAllTags();
        }
        return RestResponseUtil.success(tagCollection);
    }

}
