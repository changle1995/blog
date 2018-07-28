package com.example.blog.controller.auth;

import com.example.blog.domain.RestResponse;
import com.example.blog.entity.auth.Permission;
import com.example.blog.service.auth.PermissionService;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Permission后端权限相关操作controller
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

    @ApiOperation(value = "新增权限", notes = "新增权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "权限名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "description", value = "权限描述", paramType = "query"),
            @ApiImplicitParam(name = "url", value = "权限对应的url路径", required = true, paramType = "query"),
            @ApiImplicitParam(name = "method", value = "权限对应url路径的方法", required = true, paramType = "query")
    })
    @PostMapping("/")
    public RestResponse<Permission> add(
            @RequestParam String name,
            @RequestParam(required = false) String description,
            @RequestParam String url,
            @RequestParam String method
    ) {
        Permission permission = permissionService.addPermission(name, description, url, method);
        return RestResponseUtil.success(permission, "添加权限成功");
    }

    @ApiOperation(value = "删除权限", notes = "删除权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "权限ID", required = true, dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public RestResponse<Permission> delete(@PathVariable long id) {
        permissionService.deletePermission(id);
        return RestResponseUtil.success(null, "删除权限成功");
    }

    @ApiOperation(value = "修改权限", notes = "修改权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "权限ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "权限名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "description", value = "权限描述", paramType = "query"),
            @ApiImplicitParam(name = "url", value = "权限对应的url路径", required = true, paramType = "query"),
            @ApiImplicitParam(name = "method", value = "权限对应url路径的方法", required = true, paramType = "query")
    })
    @PutMapping("/")
    public RestResponse<Permission> edit(
            @RequestParam long id,
            @RequestParam String name,
            @RequestParam(required = false) String description,
            @RequestParam String url,
            @RequestParam String method
    ) {
        Permission permission = permissionService.editPermission(id, name, description, url, method);
        return RestResponseUtil.success(permission, "修改权限成功");
    }

    @ApiOperation(value = "查找权限", notes = "分页查找所有权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "页数", defaultValue = "0", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", defaultValue = "8", dataType = "int", paramType = "query")
    })
    @GetMapping("/")
    public RestResponse<Page<Permission>> get(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "8") int pageSize
    ) {
        return RestResponseUtil.success(permissionService.getPermissions(pageNumber, pageSize));
    }

    @ApiOperation(value = "查找权限", notes = "通过权限名称查找单个权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "权限名称", required = true, paramType = "query")
    })
    @GetMapping("/getByName")
    public RestResponse<Permission> getByName(@RequestParam String name) {
        return RestResponseUtil.success(permissionService.getPermission(name));
    }

}
