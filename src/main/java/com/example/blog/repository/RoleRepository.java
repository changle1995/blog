package com.example.blog.repository;

import com.example.blog.entity.Role;

import java.util.Collection;
import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/20
 * Time: 23:41
 */
public interface RoleRepository extends BaseRepository<Role, Long> {

    Role findByName(String name);

    List<Role> findAllByIdIn(Collection<Long> idCollection);

}
