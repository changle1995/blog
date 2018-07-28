package com.example.blog.service.auth.impl;

import com.example.blog.entity.auth.Permission;
import com.example.blog.repository.auth.PermissionRepository;
import com.example.blog.service.auth.PermissionService;
import com.example.blog.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
        Assert.isNull(permissionRepository.findByName(name), "该权限名已存在");
        return permissionRepository.save(modifyPermission(new Permission(), name, description, url, method));
    }

    @Override
    public void deletePermission(long id) {
        permissionRepository.delete(permissionRepository.findOne(id));
    }

    @Override
    public Permission editPermission(long id, String name, String description, String url, String method) {
        Assert.isNull(permissionRepository.findByName(name), "该权限名已存在");
        return permissionRepository.save(modifyPermission(permissionRepository.findOne(id), name, description, url, method));
    }

    @Override
    public Permission getPermission(String name) {
        return permissionRepository.findByName(name);
    }

    @Override
    public Page<Permission> getPermissions(int pageNumber, int pageSize) {
        Pageable pageable = new PageRequest(pageNumber, pageSize, new Sort(Sort.Direction.DESC, "id"));
        return permissionRepository.findAll(pageable);
    }

    private Permission modifyPermission(Permission permission, String name, String description, String url, String method) {
        Assert.notNull(permission, "该权限不存在");
        Assert.hasText(name, "权限名不能为空或全空白字符");
        Assert.hasText(url, "权限url不能为空或全空白字符");
        Assert.hasText(method, "权限url对应方法不能为空或全空白字符");
        permission.setName(name);
        permission.setDescription(description);
        permission.setUrl(url);
        permission.setMethod(method);
        return permission;
    }

}
