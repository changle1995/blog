package com.example.blog.service;

import com.example.blog.entity.User;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/16
 * Time: 17:19
 */
@Service
@Transactional
public class UserService extends BaseService<User> {

    @Autowired
    private UserRepository userRepository;

    public User addUser(String username, String password, String email, String phoneNumber, String description) {
        User user = userRepository.findByUsername(username);
        Assert.isNull(user, "该用户名已存在");
        user = generateUser(username, password, email, phoneNumber, description);
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        User user = userRepository.findOne(id);
        Assert.notNull(user, "该用户不存在");
        userRepository.delete(user);
    }

    public User editUser(long id, String username, String password, String email, String phoneNumber, String description) {
        User user = userRepository.findOne(id);
        Assert.notNull(user, "该用户不存在");
        modifyUser(user, username, password, email, phoneNumber, description);
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        User user = userRepository.findOne(id);
        Assert.notNull(user, "该用户不存在");
        return user;
    }

    public User getUser(String username) {
        User user = userRepository.findByUsername(username);
        Assert.notNull(user, "该用户名不存在");
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User generateUser(String username, String password, String email, String phoneNumber, String description) {
        User user = new User();
        modifyUser(user, username, password, email, phoneNumber, description);
        return user;
    }

    public void modifyUser(User user, String username, String password, String email, String phoneNumber, String description) {
        Assert.notNull(user, "待修改的用户不能为空");
        Assert.hasText(username, "用户名不能为空或全空白字符");
        if (user.getPassword() == null || !user.getPassword().equals(password)) {
            user.setPassword(password);
        }
        user.setUsername(username);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setDescription(description);
    }

}
