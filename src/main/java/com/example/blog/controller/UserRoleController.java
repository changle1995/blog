package com.example.blog.controller;

import com.example.blog.domain.User;
import com.example.blog.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/27
 * Time: 0:21
 */
@Controller
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/addRoleToUser")
    public String addRoleToUser() {
        return "/addRoleToUser";
    }

    @PostMapping("/submitAddRoleToUser")
    @ResponseBody
    public User submitAddRoleToUser(@RequestParam(name = "username") String username, @RequestParam(name = "roleName") String roleName) {
        List<String> roleNameList = new ArrayList<>();
        roleNameList.add(roleName);
        return userRoleService.addRolesToUser(username, roleNameList);
    }

    @GetMapping("/deleteRoleOfUser")
    public String deleteRoleOfUser() {
        return "/deleteRoleOfUser";
    }

    @PostMapping("/submitDeleteRoleOfUser")
    @ResponseBody
    public User submitDeleteRoleOfUser(@RequestParam(name = "username") String username, @RequestParam(name = "roleName") String roleName) {
        List<String> roleNameList = new ArrayList<>();
        roleNameList.add(roleName);
        return userRoleService.deleteRolesOfUser(username, roleNameList);
    }

}
