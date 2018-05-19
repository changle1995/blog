package com.example.blog.controller;

import com.example.blog.domain.RestResponse;
import com.example.blog.entity.Route;
import com.example.blog.service.RouteService;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Route前端路由相关操作controller
 * Author: changle
 * Date: 2018/5/17
 * Time: 17:03
 */
@Api(tags = "Route前端路由控制器", description = "Route前端路由增删改查接口")
@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @ApiOperation(value = "新增路由", notes = "新增路由")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "路由名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "路由描述", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "propertyName", value = "参数名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "propertyValue", value = "参数值", dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功")
    })
    @PostMapping("/")
    public RestResponse<Route> add(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "propertyName", required = false) String propertyName,
            @RequestParam(name = "propertyValue", required = false) String propertyValue
    ) {
        Route route = routeService.addRoute(name, description, propertyName, propertyValue);
        return RestResponseUtil.success(route, "添加路由成功");
    }

    @ApiOperation(value = "删除路由", notes = "删除路由")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "路由ID", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功")
    })
    @DeleteMapping("/{id}")
    public RestResponse<Route> delete(@PathVariable(name = "id") long id) {
        routeService.deleteRoute(id);
        return RestResponseUtil.success(null, "删除路由成功");
    }

    @ApiOperation(value = "修改路由", notes = "修改路由")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "路由ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "路由名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "路由描述", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "propertyName", value = "参数名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "propertyValue", value = "参数值", dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功")
    })
    @PutMapping("/")
    public RestResponse<Route> edit(
            @RequestParam(name = "id") long id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "propertyName", required = false) String propertyName,
            @RequestParam(name = "propertyValue", required = false) String propertyValue
    ) {
        Route route = routeService.editRoute(id, name, description, propertyName, propertyValue);
        return RestResponseUtil.success(route, "修改路由成功");
    }

    @ApiOperation(value = "查找路由", notes = "通过路由名称查找路由集合或直接查找所有路由")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "路由名称", dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查找成功")
    })
    @GetMapping("/")
    public RestResponse<Collection<Route>> get(@RequestParam(name = "name", required = false) String name) {
        Collection<Route> routeCollection;
        if (StringUtils.hasText(name)) {
            routeCollection = routeService.getRoutes(name);
        } else {
            routeCollection = routeService.getAllRoutes();
        }
        return RestResponseUtil.success(routeCollection);
    }

}
