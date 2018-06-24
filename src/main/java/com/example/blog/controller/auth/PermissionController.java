package com.example.blog.controller.auth;

import com.example.blog.domain.RestResponse;
import com.example.blog.entity.auth.Permission;
import com.example.blog.service.auth.PermissionService;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;

/**
 * Permission后端权限相关操作controller
 * Author: changle
 * Date: 2018/3/25
 * Time: 11:51
 */
@Api(tags = "Permission后端权限控制器", description = "Permission后端权限增删改查接口")
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation(value = "新增后端权限", notes = "新增后端权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "权限名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "权限描述", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "url", value = "权限对应的url路径", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "method", value = "权限对应url路径的方法", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功")
    })
    @PostMapping("/")
    public RestResponse<Permission> add(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "url") String url,
            @RequestParam(name = "method") String method
    ) {
        Permission permission = permissionService.addPermission(name, description, url, method);
        return RestResponseUtil.success(permission, "添加权限成功");
    }

    @ApiOperation(value = "删除后端权限", notes = "删除后端权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "权限ID", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功")
    })
    @DeleteMapping("/{id}")
    public RestResponse<Permission> delete(@PathVariable(name = "id") long id) {
        permissionService.deletePermission(id);
        return RestResponseUtil.success(null, "删除权限成功");
    }

    @ApiOperation(value = "修改后端权限", notes = "修改后端权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "权限ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "权限名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "权限描述", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "url", value = "权限对应的url路径", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "method", value = "权限对应url路径的方法", dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功")
    })
    @PutMapping("/")
    public RestResponse<Permission> edit(
            @RequestParam(name = "id") long id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "url", required = false) String url,
            @RequestParam(name = "method", required = false) String method
    ) {
        Permission permission = permissionService.editPermission(id, name, description, url, method);
        return RestResponseUtil.success(permission, "修改权限成功");
    }

    @ApiOperation(value = "查找后端权限", notes = "通过后端权限名称查找单个权限或直接查找所有权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "权限名称", dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查找成功")
    })
    @GetMapping("/")
    public RestResponse<Collection<Permission>> get(@RequestParam(name = "name", required = false) String name) {
        Collection<Permission> permissionCollection;
        if (StringUtils.hasText(name)) {
            permissionCollection = new HashSet<>();
            Permission permission = permissionService.getPermission(name);
            permissionCollection.add(permission);
        } else {
            permissionCollection = permissionService.getAllPermissions();
        }
        return RestResponseUtil.success(permissionCollection);
    }

}
