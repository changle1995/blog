package com.example.blog.service.auth.impl;

import com.example.blog.entity.auth.Permission;
import com.example.blog.repository.auth.PermissionRepository;
import com.example.blog.service.auth.PermissionService;
import com.example.blog.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Collection;

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
    public Permission addPermission(String name, String description, String url, String method) {
        Assert.isNull(permissionRepository.findByName(name), "该权限已存在");
        return permissionRepository.save(generatePermission(name, description, url, method));
    }

    @Override
    public void deletePermission(long id) {
        permissionRepository.delete(permissionRepository.findOne(id));
    }

    @Override
    public Permission editPermission(long id, String name, String description, String url, String method) {
        return permissionRepository.save(modifyPermission(permissionRepository.findOne(id), name, description, url, method));
    }

    @Override
    public Permission getPermission(long id) {
        return permissionRepository.findOne(id);
    }

    @Override
    public Permission getPermission(String name) {
        return permissionRepository.findByName(name);
    }

    @Override
    public Collection<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    private Permission generatePermission(String name, String description, String url, String method) {
        Assert.hasText(name, "权限名不能为空或全空白字符");
        Assert.hasText(url, "权限url不能为空或全空白字符");
        Assert.hasText(method, "权限url对应方法不能为空或全空白字符");
        return modifyPermission(new Permission(), name, description, url, method);
    }

    private Permission modifyPermission(Permission permission, String name, String description, String url, String method) {
        Assert.notNull(permission, "该权限不存在");
        if (StringUtils.hasText(name) && !name.equals(permission.getName())) {
            permission.setName(name);
        }
        if (StringUtils.hasText(description) && !description.equals(permission.getDescription())) {
            permission.setDescription(description);
        }
        if (StringUtils.hasText(url) && !url.equals(permission.getUrl())) {
            permission.setUrl(url);
        }
        if (StringUtils.hasText(method) && !description.equals(permission.getMethod())) {
            permission.setMethod(method);
        }
        return permission;
    }

}
