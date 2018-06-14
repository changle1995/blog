package com.example.blog.controller;

import com.example.blog.domain.AssignPermissionsRequestBody;
import com.example.blog.domain.RestResponse;
import com.example.blog.entity.Role;
import com.example.blog.service.RolePermissionService;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            @ApiImplicitParam(name = "assignPermissionsRequestBody", value = "角色分配权限参数对象", required = true, dataType = "AssignPermissionsRequestBody", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "角色新增权限成功")
    })
    @PostMapping("/")
    public RestResponse<Role> add(@RequestBody AssignPermissionsRequestBody assignPermissionsRequestBody) {
        Role role = rolePermissionService.addPermissionsToRole(assignPermissionsRequestBody.getRoleId(), assignPermissionsRequestBody.getPermissionIdCollection());
        return RestResponseUtil.success(role, "角色添加权限成功");
    }

    @ApiOperation(value = "角色删除权限", notes = "角色删除权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "assignPermissionsRequestBody", value = "角色分配权限参数对象", required = true, dataType = "AssignPermissionsRequestBody", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "角色删除权限成功")
    })
    @PutMapping("/")
    public RestResponse<Role> delete(@RequestBody AssignPermissionsRequestBody assignPermissionsRequestBody) {
        Role role = rolePermissionService.deletePermissionsOfRole(assignPermissionsRequestBody.getRoleId(), assignPermissionsRequestBody.getPermissionIdCollection());
        return RestResponseUtil.success(role, "角色删除权限成功");
    }

}
