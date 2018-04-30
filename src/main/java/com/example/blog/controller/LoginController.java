package com.example.blog.controller;

import com.example.blog.domain.RestResponse;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录登出controller
 * Author: changle
 * Date: 2018/3/21
 * Time: 21:09
 */
@Api(tags = "登录登出控制器", description = "包含登录与登出两个接口")
@RestController
@RequestMapping("/")
public class LoginController {

    @ApiOperation(value = "登录", notes = "登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "登陆成功"),
            @ApiResponse(code = 400, message = "参数错误或未知错误")
    })
    @PostMapping("/loginSuccess")
    public RestResponse loginSuccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return RestResponseUtil.success(authentication.getPrincipal());
    }

    @ApiOperation(value = "登出", notes = "登出接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "登出成功")
    })
    @PostMapping("/logout")
    public RestResponse logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
        }
        return RestResponseUtil.success();
    }

}
