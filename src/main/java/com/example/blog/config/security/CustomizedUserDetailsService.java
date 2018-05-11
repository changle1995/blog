package com.example.blog.config.security;

import com.example.blog.entity.User;
import com.example.blog.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * 自定义的获取用户类
 * Author: changle
 * Date: 2018/3/22
 * Time: 1:14
 */
@Service("customizedUserDetailsService")
public class CustomizedUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUser(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("用户名:【" + username + "】不存在!");
        }
    }

}
