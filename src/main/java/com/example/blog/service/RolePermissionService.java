package com.example.blog.service;

import com.example.blog.entity.Role;

import java.util.List;

/**
 * Author: changle
 * Date: 2018/5/5
 * Time: 12:33
 */
public interface RolePermissionService extends BaseService<Role> {

    /**
     * 给角色新增权限方法
     *
     * @param roleId           角色主键ID
     * @param permissionIdList 待增加的所有权限
     * @return 返回增加权限后的角色
     */
    Role addPermissionsToRole(long roleId, List<Long> permissionIdList);

    /**
     * 给角色删除权限方法
     *
     * @param roleId           用户主键ID
     * @param permissionIdList 待删除的所有权限
     * @return 返回删除权限后的角色
     */
    Role deletePermissionsOfRole(long roleId, List<Long> permissionIdList);

}
