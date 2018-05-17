package com.example.blog.repository;

import com.example.blog.entity.Permission;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/3/22
 * Time: 1:11
 */
public interface PermissionRepository extends BaseRepository<Permission, Long> {

    Permission findByName(String name);

    Collection<Permission> findAllByIdIn(Collection<Long> idCollection);

}
