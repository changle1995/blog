package com.example.blog.controller;

import com.example.blog.domain.RestResponse;
import com.example.blog.entity.User;
import com.example.blog.service.UserRoleService;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户与角色关系相关操作controller
 * Author: changle
 * Date: 2018/3/27
 * Time: 0:21
 */
@Api(tags = "用户与角色关系控制器", description = "用户增删角色接口")
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation(value = "用户新增角色", notes = "用户新增角色接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "Long", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "用户新增角色成功")
    })
    @PostMapping("/add")
    public RestResponse<User> add(@RequestParam(name = "userId") Long userId, @RequestParam(name = "roleId") Long roleId) {
        List<Long> roleIdList = new ArrayList<>();
        roleIdList.add(roleId);
        User user = userRoleService.addRolesToUser(userId, roleIdList);
        return RestResponseUtil.success(user, "用户添加角色成功");
    }

    @ApiOperation(value = "用户删除角色", notes = "用户删除角色接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "Long", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "用户删除角色成功")
    })
    @PostMapping("/delete")
    public RestResponse<User> delete(@RequestParam(name = "userId") Long userId, @RequestParam(name = "roleId") Long roleId) {
        List<Long> roleIdList = new ArrayList<>();
        roleIdList.add(roleId);
        User user = userRoleService.deleteRolesOfUser(userId, roleIdList);
        return RestResponseUtil.success(user, "用户删除角色成功");
    }

}
