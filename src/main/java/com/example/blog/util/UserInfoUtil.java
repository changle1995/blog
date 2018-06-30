package com.example.blog.util;

import com.example.blog.domain.auth.UserInfo;
import com.example.blog.entity.auth.User;
import com.example.blog.enumeration.HeaderNameEnum;
import org.springframework.beans.BeanUtils;
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
        BeanUtils.copyProperties(user, userInfo);
        userInfo.setRouteCollection(RouteUtil.getRouteCollectionByUser(user));
        return userInfo;
    }

}
