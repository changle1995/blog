package com.example.blog.controller.auth;

import com.example.blog.domain.AssignRolesRequestBody;
import com.example.blog.domain.RestResponse;
import com.example.blog.entity.auth.User;
import com.example.blog.service.auth.UserRoleService;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "用户新增角色", notes = "用户新增角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "assignRolesRequestBody", value = "用户分配角色参数对象", required = true, dataType = "AssignRolesRequestBody", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "用户新增角色成功")
    })
    @PostMapping("/")
    public RestResponse<User> add(@RequestBody AssignRolesRequestBody assignRolesRequestBody) {
        User user = userRoleService.addRolesToUser(assignRolesRequestBody.getUserId(), assignRolesRequestBody.getRoleIdCollection());
        return RestResponseUtil.success(user, "用户添加角色成功");
    }

    @ApiOperation(value = "用户删除角色", notes = "用户删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "assignRolesRequestBody", value = "用户分配角色参数对象", required = true, dataType = "AssignRolesRequestBody", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "用户删除角色成功")
    })
    @PutMapping("/")
    public RestResponse<User> delete(@RequestBody AssignRolesRequestBody assignRolesRequestBody) {
        User user = userRoleService.deleteRolesOfUser(assignRolesRequestBody.getUserId(), assignRolesRequestBody.getRoleIdCollection());
        return RestResponseUtil.success(user, "用户删除角色成功");
    }

}
