package com.example.blog.util;

import com.example.blog.domain.UserInfo;
import com.example.blog.entity.User;
import com.example.blog.enumeration.HeaderNameEnum;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * UserInfo相关工具类
 * Author: changle
 * Date: 2018/5/17
 * Time: 9:32
 */
public class UserInfoUtil {

    public static UserInfo getUserInfoByRequest(HttpServletRequest httpServletRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = new UserInfo();
        userInfo.setToken(httpServletRequest.getHeader(HeaderNameEnum.USER_TOKEN.getName()));
        userInfo.setUser(user);
        userInfo.setRouteCollection(RouteUtil.getRouteCollectionByUser(user));
        return userInfo;
    }

}
