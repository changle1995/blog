package com.example.blog.controller;

import com.example.blog.domain.RestResponse;
import com.example.blog.entity.User;
import com.example.blog.service.UserService;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation(value = "新增用户", notes = "新增用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "email", value = "用户邮箱", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "phoneNumber", value = "用户电话", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "用户描述", required = false, dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功")
    })
    @PostMapping("/add")
    public RestResponse<User> add(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, @RequestParam(name = "email", required = false) String email, @RequestParam(name = "phoneNumber", required = false) String phoneNumber, @RequestParam(name = "description", required = false) String description) {
        User user = userService.addUser(username, password, email, phoneNumber, description);
        return RestResponseUtil.success(user, "添加用户成功");
    }

    @ApiOperation(value = "删除用户", notes = "删除用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功")
    })
    @PostMapping("/delete")
    public RestResponse<User> delete(@RequestParam(name = "id") long id) {
        userService.deleteUser(id);
        return RestResponseUtil.success(null, "删除用户成功");
    }

    @ApiOperation(value = "修改用户", notes = "修改用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "username", value = "用户名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "email", value = "用户邮箱", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "phoneNumber", value = "用户电话", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "用户描述", required = false, dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功")
    })
    @PostMapping("/edit")
    public RestResponse<User> edit(@RequestParam(name = "id") long id, @RequestParam(name = "username") String username, @RequestParam(name = "password") String password, @RequestParam(name = "email", required = false) String email, @RequestParam(name = "phoneNumber", required = false) String phoneNumber, @RequestParam(name = "description", required = false) String description) {
        User user = userService.editUser(id, username, password, email, phoneNumber, description);
        return RestResponseUtil.success(user, "修改用户成功");
    }

    @ApiOperation(value = "查询单个用户", notes = "通过用户名称查询单个用户接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名称", required = true, dataType = "String", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功")
    })
    @GetMapping("/get/{username}")
    public RestResponse<User> get(@PathVariable(name = "username") String username) {
        User user = userService.getUser(username);
        return RestResponseUtil.success(user);
    }

    @ApiOperation(value = "查询所有用户", notes = "查询所有用户接口")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功")
    })
    @GetMapping("/get")
    public RestResponse<List<User>> getAll() {
        List<User> userList = userService.getAllUsers();
        return RestResponseUtil.success(userList);
    }

}
