package com.example.blog.service.auth;

import com.example.blog.entity.auth.Role;
import com.example.blog.service.BaseService;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/5/5
 * Time: 12:33
 */
public interface RolePermissionService extends BaseService<Role> {

    /**
     * 给角色新增权限方法
     *
     * @param roleId                 角色主键ID
     * @param permissionIdCollection 待增加的所有权限
     * @return 返回增加权限后的角色
     */
    Role addPermissionsToRole(long roleId, Collection<Long> permissionIdCollection);

    /**
     * 给角色删除权限方法
     *
     * @param roleId                 用户主键ID
     * @param permissionIdCollection 待删除的所有权限
     * @return 返回删除权限后的角色
     */
    Role deletePermissionsOfRole(long roleId, Collection<Long> permissionIdCollection);

}
