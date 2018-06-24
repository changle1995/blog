package com.example.blog.controller.auth;

import com.example.blog.domain.AssignRoutesRequestBody;
import com.example.blog.domain.RestResponse;
import com.example.blog.entity.auth.Role;
import com.example.blog.service.auth.RoleRouteService;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            @ApiImplicitParam(name = "assignRoutesRequestBody", value = "角色分配路由参数对象", required = true, dataType = "AssignRoutesRequestBody", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "角色新增路由成功")
    })
    @PostMapping("/")
    public RestResponse<Role> add(@RequestBody AssignRoutesRequestBody assignRoutesRequestBody) {
        Role role = roleRouteService.addRoutesToRole(assignRoutesRequestBody.getRoleId(), assignRoutesRequestBody.getRouteIdCollection());
        return RestResponseUtil.success(role, "角色添加路由成功");
    }

    @ApiOperation(value = "角色删除路由", notes = "角色删除路由")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "assignRoutesRequestBody", value = "角色分配路由参数对象", required = true, dataType = "AssignRoutesRequestBody", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "角色删除路由成功")
    })
    @PutMapping("/")
    public RestResponse<Role> delete(@RequestBody AssignRoutesRequestBody assignRoutesRequestBody) {
        Role role = roleRouteService.deleteRoutesOfRole(assignRoutesRequestBody.getRoleId(), assignRoutesRequestBody.getRouteIdCollection());
        return RestResponseUtil.success(role, "角色删除路由成功");
    }

}
