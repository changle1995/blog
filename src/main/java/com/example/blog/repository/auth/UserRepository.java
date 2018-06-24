package com.example.blog.repository.auth;

import com.example.blog.entity.auth.User;
import com.example.blog.repository.BaseRepository;

/**
 * Author: changle
 * Date: 2018/3/16
 * Time: 17:17
 */
public interface UserRepository extends BaseRepository<User, Long> {

    User findByUsername(String username);

}
