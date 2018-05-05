package com.example.blog.service.impl;

import com.example.blog.entity.Role;
import com.example.blog.entity.User;
import com.example.blog.repository.RoleRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.BaseService;
import com.example.blog.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

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
    public User addRolesToUser(long userId, List<Long> roleIdList) {
        User user = userRepository.findOne(userId);
        Assert.notNull(user, "用户不存在");
        for (long roleId : roleIdList) {
            Assert.notNull(roleRepository.findOne(roleId), "角色ID " + roleId + " 不存在,添加失败");
            for (Role role : user.getRoleList()) {
                Assert.isTrue(role.getId() != roleId, "该用户已经拥有ID " + roleId + " 角色,添加角色失败");
            }
        }
        user.getRoleList().addAll(roleRepository.findAllByIdIn(roleIdList));
        return userRepository.save(user);
    }

    @Override
    public User deleteRolesOfUser(long userId, List<Long> roleIdList) {
        User user = userRepository.findOne(userId);
        Assert.notNull(user, "用户不存在");
        List<Role> needToDeleteRoleList = new ArrayList<>();
        for (long roleId : roleIdList) {
            Assert.notNull(roleRepository.findOne(roleId), "角色ID " + roleId + " 不存在,删除失败");
            user.getRoleList().forEach(role -> {
                if (role.getId() == roleId) {
                    needToDeleteRoleList.add(role);
                }
            });
        }
        user.getRoleList().removeAll(needToDeleteRoleList);
        return userRepository.save(user);
    }

}
