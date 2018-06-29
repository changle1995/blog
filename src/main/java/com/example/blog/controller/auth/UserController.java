package com.example.blog.controller.auth;

import com.example.blog.domain.RestResponse;
import com.example.blog.entity.auth.User;
import com.example.blog.service.auth.UserService;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;

/**
 * User用户相关操作controller
 * Author: changle
 * Date: 2018/3/25
 * Time: 9:21
 */
@Api(tags = "User用户控制器", description = "User用户增删改查接口")
@RestController
@RequestMapping("${controller.auth.user.root}")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "email", value = "用户邮箱", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "phoneNumber", value = "用户电话", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "用户描述", dataType = "String", paramType = "query")
    })
    @PostMapping("${controller.auth.user.add}")
    public RestResponse<User> add(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(name = "description", required = false) String description
    ) {
        User user = userService.addUser(username, password, email, phoneNumber, description);
        return RestResponseUtil.success(user, "添加用户成功");
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    })
    @DeleteMapping("${controller.auth.user.delete}")
    public RestResponse<User> delete(@PathVariable(name = "id") long id) {
        userService.deleteUser(id);
        return RestResponseUtil.success(null, "删除用户成功");
    }

    @ApiOperation(value = "修改用户", notes = "修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "username", value = "用户名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "用户密码", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "email", value = "用户邮箱", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "phoneNumber", value = "用户电话", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "description", value = "用户描述", dataType = "String", paramType = "query")
    })
    @PutMapping("${controller.auth.user.edit}")
    public RestResponse<User> edit(
            @RequestParam(name = "id") long id,
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "password", required = false) String password,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(name = "description", required = false) String description
    ) {
        User user = userService.editUser(id, username, password, email, phoneNumber, description);
        return RestResponseUtil.success(user, "修改用户成功");
    }

    @ApiOperation(value = "查找用户", notes = "通过用户名称查找单个用户或直接查找所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名称", dataType = "String", paramType = "query")
    })
    @GetMapping("${controller.auth.user.get}")
    public RestResponse<Collection<User>> get(@RequestParam(name = "username", required = false) String username) {
        Collection<User> userCollection;
        if (StringUtils.hasText(username)) {
            userCollection = new HashSet<>();
            userCollection.add(userService.getUser(username));
        } else {
            userCollection = userService.getAllUsers();
        }
        return RestResponseUtil.success(userCollection);
    }

}
