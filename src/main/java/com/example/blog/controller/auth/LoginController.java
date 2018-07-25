package com.example.blog.controller.auth;

import com.example.blog.domain.RestResponse;
import com.example.blog.domain.auth.UserInfo;
import com.example.blog.entity.auth.User;
import com.example.blog.enumeration.HeaderNameEnum;
import com.example.blog.enumeration.RestResponseEnum;
import com.example.blog.service.auth.UserService;
import com.example.blog.util.AuthUtil;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 登录登出controller
 * Author: changle
 * Date: 2018/3/21
 * Time: 21:09
 */
@Api(tags = "登录登出等权限相关控制器", description = "包含登录成功,登录失败,登出成功与未登录四个接口")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录成功", notes = "登录成功接口,实际处理过程交给spring security")
    @PostMapping("${controller.auth.login.loginSuccess}")
    public RestResponse<UserInfo> loginSuccess(HttpServletRequest httpServletRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setLastLogin(new Date());
        userService.update(user);
        UserInfo userInfo = AuthUtil.getUserInfoByUserAndToken(SecurityContextHolder.getContext().getAuthentication().getPrincipal(), httpServletRequest.getHeader(HeaderNameEnum.USER_TOKEN.getName()));
        return RestResponseUtil.success(userInfo, "登录成功");
    }

    @ApiOperation(value = "登录失败", notes = "登录失败接口,实际处理过程交给spring security")
    @PostMapping("${controller.auth.login.loginFailure}")
    public RestResponse loginFailure() {
        return RestResponseUtil.error(RestResponseEnum.CODE_ERROR_LOGIN_FAILURE);
    }

    @ApiOperation(value = "登出", notes = "登出接口")
    @GetMapping("${controller.auth.login.logout}")
    public RestResponse logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(httpServletRequest, httpServletResponse, authentication);
        }
        return RestResponseUtil.success(null, "退出成功");
    }

    @ApiOperation(value = "未登录", notes = "未登录接口")
    @GetMapping("${controller.auth.login.unauthorized}")
    public RestResponse unauthorized() {
        return RestResponseUtil.error(RestResponseEnum.CODE_ERROR_UNAUTHORIZED);
    }

    @ApiOperation(value = "未授权", notes = "未授权接口")
    @PostMapping("${controller.auth.login.accessDenied}")
    public RestResponse accessDenied() {
        return RestResponseUtil.error(RestResponseEnum.CODE_ERROR_ACCESS_DENIED);
    }

}
