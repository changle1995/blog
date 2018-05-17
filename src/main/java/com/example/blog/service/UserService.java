package com.example.blog.service;

import com.example.blog.entity.User;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/5/5
 * Time: 12:33
 */
public interface UserService extends BaseService<User> {

    /**
     * 新增用户方法
     *
     * @param username    用户名
     * @param password    用户密码
     * @param email       用户邮箱
     * @param phoneNumber 用户电话
     * @param description 用户描述
     * @return 返回新增的用户
     */
    User addUser(String username, String password, String email, String phoneNumber, String description);

    /**
     * 删除用户方法
     *
     * @param id 用户主键ID
     * @return 无返回值
     */
    void deleteUser(long id);

    /**
     * 修改用户方法
     *
     * @param id          用户主键ID
     * @param username    用户名
     * @param password    用户密码
     * @param email       用户邮箱
     * @param phoneNumber 用户电话
     * @param description 用户描述
     * @return 返回修改后的用户
     */
    User editUser(long id, String username, String password, String email, String phoneNumber, String description);

    /**
     * 根据用户主键查找用户方法
     *
     * @param id 用户主键ID
     * @return 返回找到的用户
     */
    User getUser(long id);

    /**
     * 根据用户名查找用户方法
     *
     * @param username 用户名
     * @return 返回找到的用户
     */
    User getUser(String username);

    /**
     * 查找所有用户方法
     *
     * @return 返回所有的用户
     */
    Collection<User> getAllUsers();

}
