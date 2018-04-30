package com.example.blog.service;

import com.example.blog.entity.Role;
import com.example.blog.entity.User;
import com.example.blog.repository.RoleRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/27
 * Time: 0:41
 */
@Service
public class UserRoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User addRolesToUser(Long userId, List<Long> roleIdList) {
        User user = userRepository.findOne(userId);
        Assert.notNull(user, "用户不存在");
        for (Long roleId : roleIdList) {
            Assert.notNull(roleRepository.findOne(roleId), "角色ID " + roleId + " 不存在,添加失败");
            for (Role role : user.getRoleList()) {
                Assert.isTrue(role.getId() != roleId, "该用户已经拥有ID " + roleId + " 角色,添加角色失败");
            }
        }
        user.getRoleList().addAll(roleRepository.findAllByIdIn(roleIdList));
        return userRepository.save(user);
    }

    public User deleteRolesOfUser(Long userId, List<Long> roleIdList) {
        User user = userRepository.findOne(userId);
        Assert.notNull(user, "用户不存在");
        List<Role> needToDeleteRoleList = new ArrayList<>();
        for (Long roleId : roleIdList) {
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
