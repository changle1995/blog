package com.example.blog.service;

import com.example.blog.entity.User;

import java.util.List;

/**
 * Author: changle
 * Date: 2018/5/5
 * Time: 12:33
 */
public interface UserRoleService extends BaseService<User> {

    /**
     * 给用户新增角色方法
     *
     * @param userId     用户主键ID
     * @param roleIdList 待增加的所有角色
     * @return 返回增加角色后的用户
     */
    User addRolesToUser(long userId, List<Long> roleIdList);

    /**
     * 给用户删除角色方法
     *
     * @param userId     用户主键ID
     * @param roleIdList 待删除的所有角色
     * @return 返回删除角色后的用户
     */
    User deleteRolesOfUser(long userId, List<Long> roleIdList);

}
