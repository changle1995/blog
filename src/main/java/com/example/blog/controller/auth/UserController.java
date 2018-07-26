package com.example.blog.controller.auth;

import com.example.blog.domain.RestResponse;
import com.example.blog.domain.auth.UserInfo;
import com.example.blog.entity.auth.User;
import com.example.blog.service.auth.UserService;
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
 * User用户相关操作controller
 * Author: changle
 * Date: 2018/3/25
 * Time: 9:21
 */
@Api(tags = "User用户控制器", description = "User用户增删改查接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "email", value = "用户邮箱", paramType = "query"),
            @ApiImplicitParam(name = "phoneNumber", value = "用户电话", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "用户描述", paramType = "query"),
            @ApiImplicitParam(name = "avatar", value = "用户头像", paramType = "query")
    })
    @PostMapping("/")
    public RestResponse<UserInfo> add(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "avatar", required = false) String avatar
    ) {
        User user = userService.addUser(username, password, email, phoneNumber, description, avatar);
        return RestResponseUtil.success(AuthUtil.getUserInfoByUserAndToken(user, null), "添加用户成功");
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long", paramType = "path")
    })
    @DeleteMapping("/{id}")
    public RestResponse<UserInfo> delete(@PathVariable(name = "id") long id) {
        userService.deleteUser(id);
        return RestResponseUtil.success(null, "删除用户成功");
    }

    @ApiOperation(value = "修改用户", notes = "修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long", paramType = "query"),
            @ApiImplicitParam(name = "username", value = "用户名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "email", value = "用户邮箱", paramType = "query"),
            @ApiImplicitParam(name = "phoneNumber", value = "用户电话", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "用户描述", paramType = "query"),
            @ApiImplicitParam(name = "avatar", value = "用户头像", paramType = "query")
    })
    @PutMapping("/")
    public RestResponse<UserInfo> edit(
            @RequestParam(name = "id") long id,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "avatar", required = false) String avatar
    ) {
        User user = userService.editUser(id, username, password, email, phoneNumber, description, avatar);
        return RestResponseUtil.success(AuthUtil.getUserInfoByUserAndToken(user, null), "修改用户成功");
    }

    @ApiOperation(value = "查找用户", notes = "分页查找所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "页数", defaultValue = "0", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", defaultValue = "8", dataType = "int", paramType = "query")
    })
    @GetMapping("/")
    public RestResponse<Page<UserInfo>> get(
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", required = false, defaultValue = "8") Integer pageSize
    ) {
        return RestResponseUtil.success(userService.getUserInfos(pageNumber, pageSize));
    }

    @ApiOperation(value = "查找用户", notes = "通过用户ID查找单个用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long", paramType = "query")
    })
    @GetMapping("/getById")
    public RestResponse<UserInfo> getById(@RequestParam(name = "id") long id) {
        User user = userService.get(id);
        return RestResponseUtil.success(user == null ? null : AuthUtil.getUserInfoByUserAndToken(user, null));
    }

}
