package com.example.blog.service;

import com.example.blog.domain.Role;
import com.example.blog.domain.User;
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

    public User addRolesToUser(String username, List<String> roleNameList) {
        Assert.hasText(username, "用户名不能为空或全空白字符串");
        User user = userRepository.findByUsername(username);
        Assert.notNull(user, "用户名不存在");
        for (String roleName : roleNameList) {
            Assert.notNull(roleRepository.findByName(roleName), "角色" + roleName + "不存在,添加失败");
            for (Role role : user.getRoleList()) {
                Assert.isTrue(!role.getName().equals(roleName), "该用户已经拥有" + roleName + "角色,添加角色失败");
            }
        }
        user.getRoleList().addAll(roleRepository.findAllByNameIn(roleNameList));
        return userRepository.save(user);
    }

    public User deleteRolesOfUser(String username, List<String> roleNameList) {
        Assert.hasText(username, "用户名不能为空或全空白字符串");
        User user = userRepository.findByUsername(username);
        Assert.notNull(user, "用户名不存在");
        List<Role> needToDeleteRoleList = new ArrayList<>();
        for (String roleName : roleNameList) {
            Assert.notNull(roleRepository.findByName(roleName), "角色" + roleName + "不存在,删除失败");
            for (Role role : user.getRoleList()) {
                if (role.getName().equals(roleName)) {
                    needToDeleteRoleList.add(role);
                }
            }
        }
        user.getRoleList().removeAll(needToDeleteRoleList);
        return userRepository.save(user);
    }

}
