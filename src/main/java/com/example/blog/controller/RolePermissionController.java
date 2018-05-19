package com.example.blog.controller;

import com.example.blog.domain.RestResponse;
import com.example.blog.entity.Role;
import com.example.blog.service.RolePermissionService;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;

/**
 * 角色与权限关系相关操作controller
 * Author: changle
 * Date: 2018/3/27
 * Time: 0:21
 */
@Api(tags = "角色与权限关系控制器", description = "角色增删权限接口")
@RestController
@RequestMapping("/rolePermission")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @ApiOperation(value = "角色新增权限", notes = "角色新增权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "permissionId", value = "权限ID", required = true, dataType = "Long", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "角色新增权限成功")
    })
    @PostMapping("/")
    public RestResponse<Role> add(@RequestParam(name = "roleId") long roleId, @RequestParam(name = "permissionId") long permissionId) {
        Collection<Long> permissionIdCollection = new HashSet<>();
        permissionIdCollection.add(permissionId);
        Role role = rolePermissionService.addPermissionsToRole(roleId, permissionIdCollection);
        return RestResponseUtil.success(role, "角色添加权限成功");
    }

    @ApiOperation(value = "角色删除权限", notes = "角色删除权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "permissionId", value = "权限ID", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "角色删除权限成功")
    })
    @DeleteMapping("/{roleId}/{permissionId}")
    public RestResponse<Role> delete(@PathVariable(name = "roleId") long roleId, @PathVariable(name = "permissionId") long permissionId) {
        Collection<Long> permissionIdCollection = new HashSet<>();
        permissionIdCollection.add(permissionId);
        Role role = rolePermissionService.deletePermissionsOfRole(roleId, permissionIdCollection);
        return RestResponseUtil.success(role, "角色删除权限成功");
    }

}
