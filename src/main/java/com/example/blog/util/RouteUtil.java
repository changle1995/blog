package com.example.blog.util;

import com.example.blog.entity.auth.Route;
import com.example.blog.entity.auth.User;

import java.util.Collection;
import java.util.HashSet;

/**
 * User中的Route信息相关工具类
 * Author: changle
 * Date: 2018/5/16
 * Time: 17:05
 */
public class RouteUtil {

    public static Collection<Route> getRouteCollectionByUser(User user) {
        Collection<Route> routeCollection = new HashSet<>();
        user.getRoleSet().forEach(role -> routeCollection.addAll(role.getRouteSet()));
        return routeCollection;
    }

}
