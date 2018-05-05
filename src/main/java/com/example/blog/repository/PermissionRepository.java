package com.example.blog.repository;

import com.example.blog.entity.Permission;

import java.util.Collection;
import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/22
 * Time: 1:11
 */
public interface PermissionRepository extends BaseRepository<Permission, Long> {

    Permission findByName(String name);

    List<Permission> findAllByIdIn(Collection<Long> idCollection);

}
