package com.example.blog.service.auth;

import com.example.blog.domain.auth.UserInfo;
import com.example.blog.entity.auth.User;
import com.example.blog.service.BaseService;
import org.springframework.data.domain.Page;

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
     * @param avatar      用户头像
     * @return 返回新增的用户
     */
    User addUser(String username, String password, String email, String phoneNumber, String description, String avatar);

    /**
     * 删除用户方法
     *
     * @param id 用户主键ID
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
     * @param avatar      用户头像
     * @return 返回修改后的用户
     */
    User editUser(long id, String username, String password, String email, String phoneNumber, String description, String avatar);

    /**
     * 根据用户名查找用户方法
     *
     * @param username 用户名
     * @return 返回找到的用户
     */
    User getUser(String username);

    /**
     * 分页查找所有用户方法
     *
     * @param pageNumber 页数
     * @param pageSize   每页数量
     * @return 返回所有的用户
     */
    Page<UserInfo> getUserInfos(int pageNumber, int pageSize);

}
