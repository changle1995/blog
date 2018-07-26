package com.example.blog.controller.auth;

import com.example.blog.domain.RestResponse;
import com.example.blog.domain.auth.RoleDomain;
import com.example.blog.entity.auth.Role;
import com.example.blog.service.auth.RoleService;
import com.example.blog.util.AuthUtil;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
            @ApiImplicitParam(name = "name", value = "角色名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "description", value = "角色描述", paramType = "query")
    })
    @PostMapping("/")
    public RestResponse<RoleDomain> add(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description", required = false) String description
    ) {
        Role role = roleService.addRole(name, description);
        return RestResponseUtil.success(AuthUtil.getRoleDomainByRole(role), "添加角色成功");
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public RestResponse<RoleDomain> delete(@PathVariable(name = "id") long id) {
        roleService.deleteRole(id);
        return RestResponseUtil.success(null, "删除角色成功");
    }

    @ApiOperation(value = "修改角色", notes = "修改角色接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "角色名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "description", value = "角色描述", paramType = "query")
    })
    @PutMapping("/")
    public RestResponse<RoleDomain> edit(
            @RequestParam(name = "id") long id,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "description", required = false) String description
    ) {
        Role role = roleService.editRole(id, name, description);
        return RestResponseUtil.success(AuthUtil.getRoleDomainByRole(role), "修改角色成功");
    }

    @ApiOperation(value = "查找角色", notes = "分页查找所有角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "页数", defaultValue = "0", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", defaultValue = "8", dataType = "int", paramType = "query")
    })
    @GetMapping("/")
    public RestResponse<Page<RoleDomain>> get(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "8") Integer pageSize
    ) {
        return RestResponseUtil.success(roleService.getRoleDomains(pageNumber, pageSize));
    }

    @ApiOperation(value = "查找角色", notes = "通过角色名称查找单个角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "角色名称", paramType = "query")
    })
    @GetMapping("/getByName")
    public RestResponse<RoleDomain> getByName(@RequestParam(name = "name", required = false) String name) {
        Role role = roleService.getRole(name);
        return RestResponseUtil.success(role == null ? null : AuthUtil.getRoleDomainByRole(role));
    }

}
