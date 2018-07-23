package com.example.blog.service.auth.impl;

import com.example.blog.entity.auth.User;
import com.example.blog.repository.auth.UserRepository;
import com.example.blog.service.auth.UserService;
import com.example.blog.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/3/16
 * Time: 17:19
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(String username, String password, String email, String phoneNumber, String description, String avatar) {
        Assert.isNull(userRepository.findByUsername(username), "该用户已存在");
        return userRepository.save(modifyUser(new User(), username, password, email, phoneNumber, description, avatar));
    }

    @Override
    public void deleteUser(long id) {
        userRepository.delete(id);
    }

    @Override
    public User editUser(long id, String username, String password, String email, String phoneNumber, String description, String avatar) {
        return userRepository.save(modifyUser(userRepository.findOne(id), username, password, email, phoneNumber, description, avatar));
    }

    @Override
    public User getUser(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    private User modifyUser(User user, String username, String password, String email, String phoneNumber, String description, String avatar) {
        Assert.notNull(user, "该用户不存在");
        Assert.hasText(username, "用户名不能为空或全空白字符");
        Assert.hasText(password, "密码不能为空或全空白字符");
        user.setUsername(username);
        if (!(new BCryptPasswordEncoder().matches(password, user.getPassword()))) {
            user.setPassword(password);
        }
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setDescription(description);
        return user;
    }

}
