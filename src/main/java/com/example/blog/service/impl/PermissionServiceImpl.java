package com.example.blog.service.impl;

import com.example.blog.entity.Permission;
import com.example.blog.repository.PermissionRepository;
import com.example.blog.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/22
 * Time: 1:12
 */
@Service
@Transactional
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission addPermission(String name, String description, String url) {
        Permission permission = permissionRepository.findByName(name);
        Assert.isNull(permission, "该权限已存在");
        permission = generatePermission(name, description, url);
        return permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(long id) {
        Permission permission = permissionRepository.findOne(id);
        Assert.notNull(permission, "该权限不存在");
        permissionRepository.delete(permission);
    }

    @Override
    public Permission editPermission(long id, String name, String description, String url) {
        Permission permission = permissionRepository.findOne(id);
        Assert.notNull(permission, "该权限不存在");
        modifyPermission(permission, name, description, url);
        return permissionRepository.save(permission);
    }

    @Override
    public Permission getPermission(long id) {
        Permission permission = permissionRepository.findOne(id);
        Assert.notNull(permission, "该权限不存在");
        return permission;
    }

    @Override
    public Permission getPermission(String name) {
        Permission permission = permissionRepository.findByName(name);
        Assert.notNull(permission, "该权限不存在");
        return permission;
    }

    @Override
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    private Permission generatePermission(String name, String description, String url) {
        Permission permission = new Permission();
        modifyPermission(permission, name, description, url);
        return permission;
    }

    private void modifyPermission(Permission permission, String name, String description, String url) {
        Assert.hasText(name, "权限名不能为空或全空白字符");
        permission.setName(name);
        permission.setDescription(description);
        permission.setUrl(url);
    }

}
