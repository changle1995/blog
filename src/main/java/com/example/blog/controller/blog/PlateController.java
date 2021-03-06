package com.example.blog.controller.blog;

import com.example.blog.domain.RestResponse;
import com.example.blog.entity.blog.Plate;
import com.example.blog.service.blog.PlateService;
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
@Api(tags = "板块控制器", description = "板块增删改查接口")
@RestController
@RequestMapping("/plate")
public class PlateController {

    @Autowired
    private PlateService plateService;

    @ApiOperation(value = "新增板块", notes = "新增板块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "板块名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "description", value = "板块描述", paramType = "query"),
            @ApiImplicitParam(name = "state", value = "板块状态", defaultValue = "1", dataType = "int", paramType = "query")
    })
    @PostMapping("/")
    public RestResponse<Plate> add(
            @RequestParam String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false, defaultValue = "1") int state
    ) {
        Plate plate = plateService.addPlate(name, description, state);
        return RestResponseUtil.success(plate, "添加板块成功");
    }

    @ApiOperation(value = "删除板块", notes = "删除板块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "板块ID", required = true, dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public RestResponse<Plate> delete(@PathVariable long id) {
        plateService.deletePlate(id);
        return RestResponseUtil.success(null, "删除板块成功");
    }

    @ApiOperation(value = "修改板块", notes = "修改板块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "板块ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "板块名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "description", value = "板块描述", paramType = "query"),
            @ApiImplicitParam(name = "state", value = "板块状态", required = true, dataType = "int", paramType = "query")
    })
    @PutMapping("/")
    public RestResponse<Plate> edit(
            @RequestParam long id,
            @RequestParam String name,
            @RequestParam(required = false) String description,
            @RequestParam int state) {
        Plate plate = plateService.editPlate(id, name, description, state);
        return RestResponseUtil.success(plate, "修改板块成功");
    }

    @ApiOperation(value = "查找板块", notes = "直接查找所有板块")
    @GetMapping("/")
    public RestResponse<Collection<Plate>> get() {
        return RestResponseUtil.success(plateService.getAllPlates());
    }

}
