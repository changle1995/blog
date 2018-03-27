package com.example.blog.controller;

import com.example.blog.domain.Role;
import com.example.blog.service.RolePermissionService;
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
@RequestMapping("/rolePermission")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping("/addPermissionToRole")
    public String addPermissionToRole() {
        return "/addPermissionToRole";
    }

    @PostMapping("/submitAddPermissionToRole")
    @ResponseBody
    public Role submitAddPermissionToRole(@RequestParam(name = "roleName") String roleName, @RequestParam(name = "permissionName") String permissionName) {
        List<String> permissionNameList = new ArrayList<>();
        permissionNameList.add(permissionName);
        return rolePermissionService.addPermissionsToRole(roleName, permissionNameList);
    }

    @GetMapping("/deletePermissionOfRole")
    public String deletePermissionOfRole() {
        return "/deletePermissionOfRole";
    }

    @PostMapping("/submitDeletePermissionOfRole")
    @ResponseBody
    public Role submitDeletePermissionOfRole(@RequestParam(name = "roleName") String roleName, @RequestParam(name = "permissionName") String permissionName) {
        List<String> permissionNameList = new ArrayList<>();
        permissionNameList.add(permissionName);
        return rolePermissionService.deletePermissionsOfRole(roleName, permissionNameList);
    }

}
