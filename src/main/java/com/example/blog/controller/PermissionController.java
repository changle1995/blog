package com.example.blog.controller;

import com.example.blog.domain.RestResponse;
import com.example.blog.entity.Permission;
import com.example.blog.service.PermissionService;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Permission权限相关操作controller
 * Author: changle
 * Date: 2018/3/25
 * Time: 11:51
 */
@Api(tags = "Permission权限控制器", description = "Permission权限增删改查接口")
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation(value = "新增权限", notes = "新增权限接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "权限名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "权限描述", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "url", value = "权限对应的url路径", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功")
    })
    @PostMapping("/add")
    public RestResponse<Permission> add(@RequestParam(name = "name") String name, @RequestParam(name = "description", required = false) String description, @RequestParam(name = "url") String url) {
        Permission permission = permissionService.addPermission(name, description, url);
        return RestResponseUtil.success(permission, "添加权限成功");
    }

    @ApiOperation(value = "删除权限", notes = "删除权限接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "权限ID", required = true, dataType = "Long", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功")
    })
    @PostMapping("/delete")
    public RestResponse delete(@RequestParam(name = "id") long id) {
        permissionService.deletePermission(id);
        return RestResponseUtil.success(null, "删除权限成功");
    }

    @ApiOperation(value = "修改权限", notes = "修改权限接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "权限ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "权限名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "权限描述", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "url", value = "权限对应的url路径", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功")
    })
    @PostMapping("/edit")
    public RestResponse<Permission> edit(@RequestParam(name = "id") long id, @RequestParam(name = "name") String name, @RequestParam(name = "description", required = false) String description, @RequestParam(name = "url") String url) {
        Permission permission = permissionService.editPermission(id, name, description, url);
        return RestResponseUtil.success(permission, "修改权限成功");
    }

    @ApiOperation(value = "查询单个权限", notes = "通过权限名称查询单个权限接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "权限名称", required = true, dataType = "String", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功")
    })
    @GetMapping("/get/{name}")
    public RestResponse<Permission> get(@PathVariable(name = "name") String name) {
        Permission permission = permissionService.getPermission(name);
        return RestResponseUtil.success(permission);
    }

    @ApiOperation(value = "查询所有权限", notes = "查询所有权限接口")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功")
    })
    @GetMapping("/get")
    public RestResponse<List<Permission>> getAll() {
        List<Permission> permissionList = permissionService.getAllPermissions();
        return RestResponseUtil.success(permissionList);
    }

}
