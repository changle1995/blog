package com.example.blog.service;

import com.example.blog.entity.Role;

import java.util.List;

/**
 * Author: changle
 * Date: 2018/5/5
 * Time: 12:33
 */
public interface RoleService extends BaseService<Role> {

    /**
     * 新增角色方法
     *
     * @param name        角色名
     * @param description 角色描述
     * @return 返回新增的角色
     */
    Role addRole(String name, String description);

    /**
     * 删除角色方法
     *
     * @param id 角色主键ID
     * @return 无返回值
     */
    void deleteRole(long id);

    /**
     * 修改角色方法
     *
     * @param id          角色主键ID
     * @param name        角色名
     * @param description 角色描述
     * @return 返回修改后的角色
     */
    Role editRole(long id, String name, String description);

    /**
     * 根据角色主键查找角色方法
     *
     * @param id 角色主键ID
     * @return 返回找到的角色
     */
    Role getRole(long id);

    /**
     * 根据角色名查找角色方法
     *
     * @param name 角色名
     * @return 返回找到的角色
     */
    Role getRole(String name);

    /**
     * 查找所有角色方法
     *
     * @return 返回所有的角色
     */
    List<Role> getAllRoles();

}
