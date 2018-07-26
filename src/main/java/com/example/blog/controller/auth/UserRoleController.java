package com.example.blog.controller.auth;

import com.example.blog.domain.RestResponse;
import com.example.blog.domain.auth.AssignRolesRequestBody;
import com.example.blog.domain.auth.RoleDomain;
import com.example.blog.entity.auth.Role;
import com.example.blog.service.auth.UserRoleService;
import com.example.blog.util.AuthUtil;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @PostMapping("/")
    public RestResponse<Collection<RoleDomain>> add(@RequestBody AssignRolesRequestBody assignRolesRequestBody) {
        Collection<Role> roleCollection = userRoleService.addRolesToUser(assignRolesRequestBody.getUserId(), assignRolesRequestBody.getRoleIdCollection());
        Collection<RoleDomain> roleDomainCollection =
                Optional.ofNullable(roleCollection)
                        .orElse(new HashSet<>())
                        .stream()
                        .map(AuthUtil::getRoleDomainByRole)
                        .collect(Collectors.toSet());
        return RestResponseUtil.success(roleDomainCollection, "用户添加角色成功");
    }

    @ApiOperation(value = "用户删除角色", notes = "用户删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "assignRolesRequestBody", value = "用户分配角色参数对象", required = true, dataType = "AssignRolesRequestBody", paramType = "body")
    })
    @PutMapping("/")
    public RestResponse<Collection<RoleDomain>> delete(@RequestBody AssignRolesRequestBody assignRolesRequestBody) {
        Collection<Role> roleCollection = userRoleService.deleteRolesOfUser(assignRolesRequestBody.getUserId(), assignRolesRequestBody.getRoleIdCollection());
        Collection<RoleDomain> roleDomainCollection =
                Optional.ofNullable(roleCollection)
                        .orElse(new HashSet<>())
                        .stream()
                        .map(AuthUtil::getRoleDomainByRole)
                        .collect(Collectors.toSet());
        return RestResponseUtil.success(roleDomainCollection, "用户删除角色成功");
    }

}
