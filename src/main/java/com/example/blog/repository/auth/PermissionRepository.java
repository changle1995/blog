package com.example.blog.repository.auth;

import com.example.blog.entity.auth.Permission;
import com.example.blog.repository.BaseRepository;

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
