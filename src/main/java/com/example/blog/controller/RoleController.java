package com.example.blog.controller;

import com.example.blog.domain.Role;
import com.example.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/25
 * Time: 11:28
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public Role add(@RequestParam(name = "name") String name, @RequestParam(name = "description", required = false) String description) {
        return roleService.addRole(name, description);
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = "name") String name) {
        roleService.deleteRole(name);
        return "成功删除角色" + name;
    }

    @PostMapping("/edit")
    public String edit(@RequestParam(name = "name") String name, @RequestParam(name = "description", required = false) String description) {
        roleService.editRole(name, description);
        return "成功修改" + name + "角色的信息";
    }

    @GetMapping("/get/{name}")
    public Role get(@PathVariable(name = "name") String name) {
        return roleService.getRole(name);
    }

    @GetMapping("/get")
    public List<Role> getAll() {
        return roleService.getAllRoles();
    }

}
