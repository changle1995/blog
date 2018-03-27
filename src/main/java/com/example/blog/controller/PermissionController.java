package com.example.blog.controller;

import com.example.blog.domain.Permission;
import com.example.blog.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/25
 * Time: 11:51
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/add")
    public Permission add(@RequestParam(name = "name") String name, @RequestParam(name = "description", required = false) String description, @RequestParam(name = "url") String url) {
        return permissionService.addPermission(name, description, url);
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name = "name") String name) {
        permissionService.deletePermission(name);
        return "成功删除许可" + name;
    }

    @PostMapping("/edit")
    public String edit(@RequestParam(name = "name") String name, @RequestParam(name = "description", required = false) String description, @RequestParam(name = "url") String url) {
        permissionService.editPermission(name, description, url);
        return "成功修改" + name + "许可的信息";
    }

    @GetMapping("/get/{name}")
    public Permission get(@PathVariable(name = "name") String name) {
        return permissionService.getPermission(name);
    }

    @GetMapping("/get")
    public List<Permission> getAll() {
        return permissionService.getAllPermissions();
    }
}
