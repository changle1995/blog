package com.example.blog.controller.auth;

import com.example.blog.domain.RestResponse;
import com.example.blog.domain.auth.UserInfo;
import com.example.blog.enumeration.RestResponseEnum;
import com.example.blog.util.RestResponseUtil;
import com.example.blog.util.UserInfoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(tags = "登录登出等权限相关控制器", description = "包含登录成功,登录失败,登出成功与未登录四个接口")
@RestController
@RequestMapping("/")
public class LoginController {

    @ApiOperation(value = "登录成功", notes = "登录成功接口,实际处理过程交给spring security")
    @PostMapping("/loginSuccess")
    public RestResponse<UserInfo> loginSuccess(HttpServletRequest httpServletRequest) {
        UserInfo userInfo = UserInfoUtil.getUserInfoByRequest(httpServletRequest);
        return RestResponseUtil.success(userInfo, "登录成功");
    }

    @ApiOperation(value = "登录失败", notes = "登录失败接口,实际处理过程交给spring security")
    @PostMapping("/loginFailure")
    public RestResponse loginFailure() {
        return RestResponseUtil.error(RestResponseEnum.CODE_ERROR_LOGIN_FAILURE);
    }

    @ApiOperation(value = "登出", notes = "登出接口")
    @GetMapping("/logoutSuccess")
    public RestResponse logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
        }
        return RestResponseUtil.success(null, "退出成功");
    }

    @ApiOperation(value = "未登录", notes = "未登录接口")
    @GetMapping("/unauthorized")
    public RestResponse unauthorized() {
        return RestResponseUtil.error(RestResponseEnum.CODE_ERROR_UNAUTHORIZED);
    }

    @ApiOperation(value = "未授权", notes = "未授权接口")
    @PostMapping("/accessDenied")
    public RestResponse accessDenied() {
        return RestResponseUtil.error(RestResponseEnum.CODE_ERROR_ACCESS_DENIED);
    }

}
