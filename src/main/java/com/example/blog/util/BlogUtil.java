package com.example.blog.util;

import com.example.blog.entity.auth.Route;
import com.example.blog.entity.auth.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

/**
 * 博客相关工具类
 * Author: changle
 * Date: 2018/7/25
 * Time: 14:23
 */
public class BlogUtil {

    /**
     * 获取用户下所有的路由
     *
     * @param user 用户
     * @return 返回用户下所有的路由
     */
    public static Collection<Route> getRouteCollectionByUser(User user) {
        Collection<Route> routeCollection = new HashSet<>();
        Optional.ofNullable(user.getRoleSet())
                .orElse(new HashSet<>())
                .forEach(role -> routeCollection.addAll(role.getRouteSet()));
        return routeCollection;
    }

}
