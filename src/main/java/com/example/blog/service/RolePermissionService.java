package com.example.blog.service;

import com.example.blog.entity.Permission;
import com.example.blog.entity.Role;
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

    public Role addPermissionsToRole(Long roleId, List<Long> permissionIdList) {
        Role role = roleRepository.findOne(roleId);
        Assert.notNull(role, "角色不存在");
        for (Long permissionId : permissionIdList) {
            Assert.notNull(permissionRepository.findOne(permissionId), "权限ID " + permissionId + " 不存在,添加失败");
            for (Permission permission : role.getPermissionList()) {
                Assert.isTrue(permission.getId() != permissionId, "该角色已经拥有ID " + permissionId + " 权限,添加许可失败");
            }
        }
        role.getPermissionList().addAll(permissionRepository.findAllByIdIn(permissionIdList));
        return roleRepository.save(role);
    }

    public Role deletePermissionsOfRole(Long roleId, List<Long> permissionIdList) {
        Role role = roleRepository.findOne(roleId);
        Assert.notNull(role, "角色不存在");
        List<Permission> needToDeletePermissionList = new ArrayList<>();
        for (Long permissionId : permissionIdList) {
            Assert.notNull(permissionRepository.findOne(permissionId), "权限ID " + permissionId + " 不存在,删除失败");
            role.getPermissionList().forEach(permission -> {
                if (permission.getId() == permissionId) {
                    needToDeletePermissionList.add(permission);
                }
            });
        }
        role.getPermissionList().removeAll(needToDeletePermissionList);
        return roleRepository.save(role);
    }

}
