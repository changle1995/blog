package com.example.blog.service;

import com.example.blog.entity.Permission;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/5/5
 * Time: 12:32
 */
public interface PermissionService extends BaseService<Permission> {

    /**
     * 新增权限方法
     *
     * @param name        权限名
     * @param description 权限描述
     * @param url         权限对应的url路径
     * @return 返回新增的权限
     */
    Permission addPermission(String name, String description, String url);

    /**
     * 删除权限方法
     *
     * @param id 权限主键ID
     * @return 无返回值
     */
    void deletePermission(long id);

    /**
     * 修改权限方法
     *
     * @param id          权限主键ID
     * @param description 权限描述
     * @param url         权限对应的url路径
     * @return 返回修改后的权限
     */
    Permission editPermission(long id, String name, String description, String url);

    /**
     * 根据权限主键查找权限方法
     *
     * @param id 权限主键ID
     * @return 返回找到的权限
     */
    Permission getPermission(long id);

    /**
     * 根据权限名查找权限方法
     *
     * @param name 权限名
     * @return 返回找到的权限
     */
    Permission getPermission(String name);

    /**
     * 查找所有权限方法
     *
     * @return 返回所有的权限
     */
    Collection<Permission> getAllPermissions();

}
