package com.example.blog.service.auth;

import com.example.blog.entity.auth.Permission;
import com.example.blog.service.BaseService;
import org.springframework.data.domain.Page;

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
     * @param method      权限对应的url路径的方法
     * @return 返回新增的权限
     */
    Permission addPermission(String name, String description, String url, String method);

    /**
     * 删除权限方法
     *
     * @param id 权限主键ID
     */
    void deletePermission(long id);

    /**
     * 修改权限方法
     *
     * @param id          权限主键ID
     * @param name        权限名
     * @param description 权限描述
     * @param url         权限对应的url路径
     * @param method      权限对应的url路径的方法
     * @return 返回修改后的权限
     */
    Permission editPermission(long id, String name, String description, String url, String method);

    /**
     * 根据权限名查找权限方法
     *
     * @param name 权限名
     * @return 返回找到的权限
     */
    Permission getPermission(String name);

    /**
     * 分页查找所有权限方法
     *
     * @param pageNumber 页数
     * @param pageSize   每页数量
     * @return 返回所有的权限
     */
    Page<Permission> getPermissions(int pageNumber, int pageSize);

}
