package com.example.blog.service;

import com.example.blog.entity.Role;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/5/18
 * Time: 22:04
 */
public interface RoleRouteService extends BaseService<Role> {

    /**
     * 给角色新增路由方法
     *
     * @param roleId            角色主键ID
     * @param routeIdCollection 待增加的所有路由
     * @return 返回增加路由后的角色
     */
    Role addRoutesToRole(long roleId, Collection<Long> routeIdCollection);

    /**
     * 给角色删除路由方法
     *
     * @param roleId            用户主键ID
     * @param routeIdCollection 待删除的所有路由
     * @return 返回删除路由后的角色
     */
    Role deleteRoutesOfRole(long roleId, Collection<Long> routeIdCollection);

}
