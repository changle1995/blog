package com.example.blog.repository.auth;

import com.example.blog.entity.auth.Role;
import com.example.blog.repository.BaseRepository;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/3/20
 * Time: 23:41
 */
public interface RoleRepository extends BaseRepository<Role, Long> {

    Role findByName(String name);

    Collection<Role> findAllByIdIn(Collection<Long> idCollection);

}
