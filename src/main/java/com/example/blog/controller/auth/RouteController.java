package com.example.blog.controller.auth;

import com.example.blog.domain.RestResponse;
import com.example.blog.entity.auth.Route;
import com.example.blog.entity.auth.User;
import com.example.blog.service.auth.RouteService;
import com.example.blog.util.BlogUtil;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Route前端路由相关操作controller
 * Author: changle
 * Date: 2018/5/17
 * Time: 17:03
 */
@Api(tags = "Route路由控制器", description = "Route路由增删改查接口")
@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @ApiOperation(value = "新增路由", notes = "新增路由")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "路由名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "description", value = "路由描述", paramType = "query"),
            @ApiImplicitParam(name = "propertyName", value = "参数名称", paramType = "query"),
            @ApiImplicitParam(name = "propertyValue", value = "参数值", paramType = "query"),
            @ApiImplicitParam(name = "level", value = "路由级别", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "parentName", value = "父路由名称", paramType = "query")
    })
    @PostMapping("/")
    public RestResponse<Route> add(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "propertyName", required = false) String propertyName,
            @RequestParam(name = "propertyValue", required = false) String propertyValue,
            @RequestParam(name = "level", required = false) Integer level,
            @RequestParam(name = "parentName", required = false) String parentName
    ) {
        Route route = routeService.addRoute(name, description, propertyName, propertyValue, level, parentName);
        return RestResponseUtil.success(route, "添加路由成功");
    }

    @ApiOperation(value = "删除路由", notes = "删除路由")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "路由ID", required = true, dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public RestResponse<Route> delete(@PathVariable(name = "id") long id) {
        routeService.deleteRoute(id);
        return RestResponseUtil.success(null, "删除路由成功");
    }

    @ApiOperation(value = "修改路由", notes = "修改路由")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "路由ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "路由名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "description", value = "路由描述", paramType = "query"),
            @ApiImplicitParam(name = "propertyName", value = "参数名称", paramType = "query"),
            @ApiImplicitParam(name = "propertyValue", value = "参数值", paramType = "query"),
            @ApiImplicitParam(name = "level", value = "路由级别", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "parentName", value = "父路由名称", paramType = "query")
    })
    @PutMapping("/")
    public RestResponse<Route> edit(
            @RequestParam(name = "id") long id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "propertyName", required = false) String propertyName,
            @RequestParam(name = "propertyValue", required = false) String propertyValue,
            @RequestParam(name = "level", required = false) Integer level,
            @RequestParam(name = "parentName", required = false) String parentName
    ) {
        Route route = routeService.editRoute(id, name, description, propertyName, propertyValue, level, parentName);
        return RestResponseUtil.success(route, "修改路由成功");
    }

    @ApiOperation(value = "查找路由", notes = "分页查找所有路由")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "页数", defaultValue = "0", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", defaultValue = "8", dataType = "int", paramType = "query")
    })
    @GetMapping("/")
    public RestResponse<Page<Route>> get(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "8") Integer pageSize
    ) {
        return RestResponseUtil.success(routeService.getRoutes(pageNumber, pageSize));
    }

    @ApiOperation(value = "查找路由", notes = "通过路由名称查找路由集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "路由名称", required = true, paramType = "query")
    })
    @GetMapping("/getByName")
    public RestResponse<Collection<Route>> getByName(@RequestParam(name = "name") String name) {
        return RestResponseUtil.success(routeService.getRoutes(name));
    }

    @ApiOperation(value = "查找已拥有路由", notes = "查找已拥有路由")
    @GetMapping("/getUserRoutes")
    public RestResponse<Collection<Route>> getUserRoutes() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return RestResponseUtil.success(BlogUtil.getRouteCollectionByUser((User) principal));
        }
        return RestResponseUtil.success(null);
    }

}
