package com.example.blog.service;

import com.example.blog.domain.Permission;
import com.example.blog.repository.PermissionRepository;
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
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission addPermission(String name, String description, String url) {
        Permission permission = permissionRepository.findByName(name);
        Assert.isNull(permission, "该许可已存在");
        permission = generatePermission(name, description, url);
        return permissionRepository.save(permission);
    }

    public void deletePermission(String name) {
        Assert.hasText(name, "许可名不能为空或全空白字符");
        Permission permission = permissionRepository.findByName(name);
        Assert.notNull(permission, "该许可不存在");
        permissionRepository.delete(permission);
    }

    public void editPermission(String name, String description, String url) {
        Permission permission = permissionRepository.findByName(name);
        Assert.notNull(permission, "该许可不存在");
        modifyPermission(permission, name, description, url);
        permissionRepository.save(permission);
    }

    public Permission getPermission(Long id) {
        return permissionRepository.findOne(id);
    }

    public Permission getPermission(String name) {
        return permissionRepository.findByName(name);
    }

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    public Permission generatePermission(String name, String description, String url) {
        Permission permission = new Permission();
        modifyPermission(permission, name, description, url);
        return permission;
    }

    public void modifyPermission(Permission permission, String name, String description, String url) {
        Assert.notNull(permission, "待修改的许可不能为空");
        Assert.hasText(name, "许可名不能为空或全空白字符");
        permission.setName(name);
        permission.setDescription(description);
        permission.setUrl(url);
    }

}
