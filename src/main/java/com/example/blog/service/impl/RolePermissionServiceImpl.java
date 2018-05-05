package com.example.blog.service.impl;

import com.example.blog.entity.Permission;
import com.example.blog.entity.Role;
import com.example.blog.repository.PermissionRepository;
import com.example.blog.repository.RoleRepository;
import com.example.blog.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/28
 * Time: 1:49
 */
@Service
@Transactional
public class RolePermissionServiceImpl extends BaseServiceImpl<Role> implements RolePermissionService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Role addPermissionsToRole(long roleId, List<Long> permissionIdList) {
        Role role = roleRepository.findOne(roleId);
        Assert.notNull(role, "角色不存在");
        for (long permissionId : permissionIdList) {
            Assert.notNull(permissionRepository.findOne(permissionId), "权限ID " + permissionId + " 不存在,添加失败");
            for (Permission permission : role.getPermissionList()) {
                Assert.isTrue(permission.getId() != permissionId, "该角色已经拥有ID " + permissionId + " 权限,添加权限失败");
            }
        }
        role.getPermissionList().addAll(permissionRepository.findAllByIdIn(permissionIdList));
        return roleRepository.save(role);
    }

    @Override
    public Role deletePermissionsOfRole(long roleId, List<Long> permissionIdList) {
        Role role = roleRepository.findOne(roleId);
        Assert.notNull(role, "角色不存在");
        List<Permission> needToDeletePermissionList = new ArrayList<>();
        for (long permissionId : permissionIdList) {
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
