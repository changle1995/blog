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
import org.springframework.util.StringUtils;

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
    public User addUser(String username, String password, String email, String phoneNumber, String description) {
        User user = userRepository.findByUsername(username);
        Assert.isNull(user, "该用户已存在");
        user = generateUser(username, password, email, phoneNumber, description);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = userRepository.findOne(id);
        Assert.notNull(user, "该用户不存在");
        userRepository.delete(user);
    }

    @Override
    public User editUser(long id, String username, String password, String email, String phoneNumber, String description) {
        User user = userRepository.findOne(id);
        Assert.notNull(user, "该用户不存在");
        modifyUser(user, username, password, email, phoneNumber, description);
        return userRepository.save(user);
    }

    @Override
    public User getUser(long id) {
        User user = userRepository.findOne(id);
        Assert.notNull(user, "该用户不存在");
        return user;
    }

    @Override
    public User getUser(String username) {
        User user = userRepository.findByUsername(username);
        Assert.notNull(user, "该用户不存在");
        return user;
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    private User generateUser(String username, String password, String email, String phoneNumber, String description) {
        Assert.hasText(username, "用户名不能为空或全空白字符");
        Assert.hasText(password, "密码不能为空或全空白字符");
        User user = new User();
        modifyUser(user, username, password, email, phoneNumber, description);
        return user;
    }

    private void modifyUser(User user, String username, String password, String email, String phoneNumber, String description) {
        Assert.notNull(user, "用户不能为空");
        if (StringUtils.hasText(username) && !username.equals(user.getUsername())) {
            user.setUsername(username);
        }
        if (StringUtils.hasText(password) && !(new BCryptPasswordEncoder().matches(password, user.getPassword()))) {
            user.setPassword(password);
        }
        if (StringUtils.hasText(email) && !email.equals(user.getEmail())) {
            user.setEmail(email);
        }
        if (StringUtils.hasText(phoneNumber) && !phoneNumber.equals(user.getPhoneNumber())) {
            user.setPhoneNumber(phoneNumber);
        }
        if (StringUtils.hasText(description) && !description.equals(user.getDescription())) {
            user.setDescription(description);
        }
    }

}
