package com.example.blog.service;

import com.example.blog.domain.Permission;
import com.example.blog.domain.Role;
import com.example.blog.repository.PermissionRepository;
import com.example.blog.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/28
 * Time: 1:49
 */
@Service
public class RolePermissionService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public Role addPermissionsToRole(String roleName, List<String> permissionNameList) {
        Assert.hasText(roleName, "角色名不能为空或全空白字符串");
        Role role = roleRepository.findByName(roleName);
        Assert.notNull(role, "角色不存在");
        for (String permissionName : permissionNameList) {
            Assert.notNull(permissionRepository.findByName(permissionName), "许可" + roleName + "不存在,添加失败");
            for (Permission permission : role.getPermissionList()) {
                Assert.isTrue(!permission.getName().equals(permissionName), "该角色已经拥有" + permissionName + "许可,添加许可失败");
            }
        }
        role.getPermissionList().addAll(permissionRepository.findAllByNameIn(permissionNameList));
        return roleRepository.save(role);
    }

    public Role deletePermissionsOfRole(String roleName, List<String> permissionNameList) {
        Assert.hasText(roleName, "角色名不能为空或全空白字符串");
        Role role = roleRepository.findByName(roleName);
        Assert.notNull(role, "角色不存在");
        List<Permission> needToDeletePermissionList = new ArrayList<>();
        for (String permissionName : permissionNameList) {
            Assert.notNull(permissionRepository.findByName(permissionName), "许可" + roleName + "不存在,删除失败");
            for (Permission permission : role.getPermissionList()) {
                if (role.getName().equals(roleName)) {
                    needToDeletePermissionList.add(permission);
                }
            }
        }
        role.getPermissionList().removeAll(needToDeletePermissionList);
        return roleRepository.save(role);
    }

}
