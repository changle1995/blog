package com.example.blog.controller;

import com.example.blog.domain.RestResponse;
import com.example.blog.entity.Role;
import com.example.blog.service.RoleRouteService;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;

/**
 * 角色与路由关系相关操作controller
 * Author: changle
 * Date: 2018/5/18
 * Time: 21:21
 */
@Api(tags = "角色与路由关系控制器", description = "角色增删路由接口")
@RestController
@RequestMapping("/roleRoute")
public class RoleRouteController {

    @Autowired
    private RoleRouteService roleRouteService;

    @ApiOperation(value = "角色新增路由", notes = "角色新增路由")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "routeId", value = "路由ID", required = true, dataType = "Long", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "角色新增路由成功")
    })
    @PostMapping("/")
    public RestResponse<Role> add(@RequestParam(name = "roleId") long roleId, @RequestParam(name = "routeId") long routeId) {
        Collection<Long> routeIdCollection = new HashSet<>();
        routeIdCollection.add(routeId);
        Role role = roleRouteService.addRoutesToRole(roleId, routeIdCollection);
        return RestResponseUtil.success(role, "角色添加路由成功");
    }

    @ApiOperation(value = "角色删除路由", notes = "角色删除路由")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "routeId", value = "路由ID", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "角色删除路由成功")
    })
    @DeleteMapping("/{roleId}/{routeId}")
    public RestResponse<Role> delete(@PathVariable(name = "roleId") long roleId, @PathVariable(name = "routeId") long routeId) {
        Collection<Long> routeIdCollection = new HashSet<>();
        routeIdCollection.add(routeId);
        Role role = roleRouteService.deleteRoutesOfRole(roleId, routeIdCollection);
        return RestResponseUtil.success(role, "角色删除路由成功");
    }

}
