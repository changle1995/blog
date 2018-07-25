package com.example.blog.util;

import com.example.blog.domain.auth.UserInfo;
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

}
