package com.example.blog.service.impl;

import com.example.blog.entity.Role;
import com.example.blog.repository.PermissionRepository;
import com.example.blog.repository.RoleRepository;
import com.example.blog.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;

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
    public Role addPermissionsToRole(long roleId, Collection<Long> permissionIdCollection) {
        Role role = roleRepository.findOne(roleId);
        Assert.notNull(role, "角色不存在");
        role.getPermissionSet().addAll(permissionRepository.findAllByIdIn(permissionIdCollection));
        return roleRepository.save(role);
    }

    @Override
    public Role deletePermissionsOfRole(long roleId, Collection<Long> permissionIdCollection) {
        Role role = roleRepository.findOne(roleId);
        Assert.notNull(role, "角色不存在");
        role.getPermissionSet().removeAll(permissionRepository.findAllByIdIn(permissionIdCollection));
        return roleRepository.save(role);
    }

}
