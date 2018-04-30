package com.example.blog.repository;

import com.example.blog.entity.User;

/**
 * Author: changle
 * Date: 2018/3/16
 * Time: 17:17
 */
public interface UserRepository extends BaseRepository<User, Long> {

    public User findByUsername(String username);
}
