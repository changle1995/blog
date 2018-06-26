package com.example.blog.controller.blog;

import com.example.blog.domain.RestResponse;
import com.example.blog.entity.blog.Plate;
import com.example.blog.service.blog.PlateService;
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
@Api(tags = "板块控制器", description = "板块增删改查接口")
@RestController
@RequestMapping("/plate")
public class PlateController {

    @Autowired
    private PlateService plateService;

    @ApiOperation(value = "新增板块", notes = "新增板块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "板块名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "板块描述", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "state", value = "板块状态", dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功")
    })
    @PostMapping("/")
    public RestResponse<Plate> add(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "state", required = false) Integer state
    ) {
        Plate plate = plateService.addPlate(name, description, state);
        return RestResponseUtil.success(plate, "添加板块成功");
    }

    @ApiOperation(value = "删除板块", notes = "删除板块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "板块ID", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功")
    })
    @DeleteMapping("/{id}")
    public RestResponse<Plate> delete(@PathVariable(name = "id") long id) {
        plateService.deletePlate(id);
        return RestResponseUtil.success(null, "删除板块成功");
    }

    @ApiOperation(value = "修改板块", notes = "修改板块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "板块ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "板块名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "板块描述", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "state", value = "板块状态", dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功")
    })
    @PutMapping("/")
    public RestResponse<Plate> edit(
            @RequestParam(name = "id") long id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "state", required = false) Integer state) {
        Plate plate = plateService.editPlate(id, name, description, state);
        return RestResponseUtil.success(plate, "修改板块成功");
    }

    @ApiOperation(value = "查找板块", notes = "通过板块名称查找板块或直接查找所有板块")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "板块名称", dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查找成功")
    })
    @GetMapping("/")
    public RestResponse<Collection<Plate>> get(@RequestParam(name = "name", required = false) String name) {
        Collection<Plate> plateCollection;
        if (StringUtils.hasText(name)) {
            plateCollection = new HashSet<>();
            Plate plate = plateService.getPlate(name);
            plateCollection.add(plate);
        } else {
            plateCollection = plateService.getAllPlates();
        }
        return RestResponseUtil.success(plateCollection);
    }

}