package com.example.blog.controller;

import com.example.blog.domain.User;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/25
 * Time: 9:21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User add(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, @RequestParam(name = "email", required = false) String email, @RequestParam(name = "phoneNumber", required = false) String phoneNumber, @RequestParam(name = "description", required = false) String description) {
        return userService.addUser(username, password, email, phoneNumber, description);
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = "username") String username) {
        userService.deleteUser(username);
        return "成功删除用户" + username;
    }

    @PostMapping("/edit")
    public String edit(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, @RequestParam(name = "email", required = false) String email, @RequestParam(name = "phoneNumber", required = false) String phoneNumber, @RequestParam(name = "description", required = false) String description) {
        userService.editUser(username, password, email, phoneNumber, description);
        return "成功修改" + username + "用户的信息";
    }

    @GetMapping("/get/{username}")
    public User get(@PathVariable(name = "username") String username) {
        return userService.getUser(username);
    }

    @GetMapping("/get")
    public List<User> getAll() {
        return userService.getAllUsers();
    }

}
