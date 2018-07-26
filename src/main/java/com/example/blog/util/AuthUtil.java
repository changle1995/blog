package com.example.blog.util;

import com.example.blog.domain.auth.RoleDomain;
import com.example.blog.domain.auth.UserInfo;
import com.example.blog.entity.auth.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

/**
 * 权限相关工具类
 * Author: changle
 * Date: 2018/7/25
 * Time: 14:25
 */
public class AuthUtil {

    /**
     * 提取用户信息包装为UserInfo
     *
     * @param user  用户
     * @param token user-token
     * @return 返回用户对应的UserInfo
     */
    public static UserInfo getUserInfoByUserAndToken(Object user, String token) {
        Assert.notNull(user, "用户不能为空");
        UserInfo userInfo = new UserInfo();
        userInfo.setToken(token);
        BeanUtils.copyProperties(user, userInfo);
        return userInfo;
    }

    /**
     * 提取角色信息包装为RoleDomain
     *
     * @param role 角色
     * @return 返回角色对应的RoleDomain
     */
    public static RoleDomain getRoleDomainByRole(Role role) {
        Assert.notNull(role, "角色不能为空");
        RoleDomain roleDomain = new RoleDomain();
        BeanUtils.copyProperties(role, roleDomain);
        return roleDomain;
    }

}
