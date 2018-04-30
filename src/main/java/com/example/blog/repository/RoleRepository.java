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

    public Role findByName(String name);

    public List<Role> findAllByNameIn(Collection<String> nameCollection);

    public List<Role> findAllByIdIn(Collection<Long> idCollection);
}
