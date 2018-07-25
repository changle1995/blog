package com.example.blog.util;

import com.example.blog.domain.auth.UserInfo;
import org.springframework.beans.BeanUtils;

/**
 * UserInfo相关工具类
 * Author: changle
 * Date: 2018/5/17
 * Time: 9:32
 */
public class UserInfoUtil {

    public static UserInfo getUserInfoByRequest(Object user, String token) {
        UserInfo userInfo = new UserInfo();
        userInfo.setToken(token);
        BeanUtils.copyProperties(user, userInfo);
        return userInfo;
    }

}
