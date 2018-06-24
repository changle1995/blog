package com.example.blog.service.auth.impl;

import com.example.blog.entity.auth.User;
import com.example.blog.repository.auth.RoleRepository;
import com.example.blog.repository.auth.UserRepository;
import com.example.blog.service.auth.UserRoleService;
import com.example.blog.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/3/27
 * Time: 0:41
 */
@Service
@Transactional
public class UserRoleServiceImpl extends BaseServiceImpl<User> implements UserRoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User addRolesToUser(long userId, Collection<Long> roleIdCollection) {
        User user = userRepository.findOne(userId);
        Assert.notNull(user, "用户不存在");
        user.getRoleSet().addAll(roleRepository.findAllByIdIn(roleIdCollection));
        return userRepository.save(user);
    }

    @Override
    public User deleteRolesOfUser(long userId, Collection<Long> roleIdCollection) {
        User user = userRepository.findOne(userId);
        Assert.notNull(user, "用户不存在");
        user.getRoleSet().removeAll(roleRepository.findAllByIdIn(roleIdCollection));
        return userRepository.save(user);
    }

}
