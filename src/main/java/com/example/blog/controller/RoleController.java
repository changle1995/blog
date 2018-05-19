package com.example.blog.controller;

import com.example.blog.domain.RestResponse;
import com.example.blog.entity.Role;
import com.example.blog.service.RoleService;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;

/**
 * Role角色相关操作controller
 * Author: changle
 * Date: 2018/3/25
 * Time: 11:28
 */
@Api(tags = "Role角色控制器", description = "Role角色增删改查接口")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "新增角色", notes = "新增角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "角色名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "角色描述", dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功")
    })
    @PostMapping("/")
    public RestResponse<Role> add(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description", required = false) String description
    ) {
        Role role = roleService.addRole(name, description);
        return RestResponseUtil.success(role, "添加角色成功");
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功")
    })
    @DeleteMapping("/{id}")
    public RestResponse<Role> delete(@PathVariable(name = "id") long id) {
        roleService.deleteRole(id);
        return RestResponseUtil.success(null, "删除角色成功");
    }

    @ApiOperation(value = "修改角色", notes = "修改角色接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "角色名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "角色描述", dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功")
    })
    @PutMapping("/")
    public RestResponse<Role> edit(
            @RequestParam(name = "id") long id,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "description", required = false) String description
    ) {
        Role role = roleService.editRole(id, name, description);
        return RestResponseUtil.success(role, "修改角色成功");
    }

    @ApiOperation(value = "查找角色", notes = "通过角色名称查找单个角色或直接查找所有角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "角色名称", dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查找成功")
    })
    @GetMapping("/")
    public RestResponse<Collection<Role>> get(@RequestParam(name = "name", required = false) String name) {
        Collection<Role> roleCollection;
        if (StringUtils.hasText(name)) {
            roleCollection = new HashSet<>();
            Role role = roleService.getRole(name);
            roleCollection.add(role);
        } else {
            roleCollection = roleService.getAllRoles();
        }
        return RestResponseUtil.success(roleCollection);
    }

}
