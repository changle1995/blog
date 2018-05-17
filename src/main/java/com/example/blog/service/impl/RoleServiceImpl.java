package com.example.blog.service.impl;

import com.example.blog.entity.Role;
import com.example.blog.repository.RoleRepository;
import com.example.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/3/20
 * Time: 23:42
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role addRole(String name, String description) {
        Role role = roleRepository.findByName(name);
        Assert.isNull(role, "该角色已存在");
        role = generateRole(name, description);
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(long id) {
        Role role = roleRepository.findOne(id);
        Assert.notNull(role, "该角色不存在");
        roleRepository.delete(role);
    }

    @Override
    public Role editRole(long id, String name, String description) {
        Role role = roleRepository.findOne(id);
        Assert.notNull(role, "该角色不存在");
        modifyRole(role, name, description);
        return roleRepository.save(role);
    }

    @Override
    public Role getRole(long id) {
        Role role = roleRepository.findOne(id);
        Assert.notNull(role, "该角色不存在");
        return role;
    }

    @Override
    public Role getRole(String name) {
        Role role = roleRepository.findByName(name);
        Assert.notNull(role, "该角色不存在");
        return role;
    }

    @Override
    public Collection<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    private Role generateRole(String name, String description) {
        Role role = new Role();
        modifyRole(role, name, description);
        return role;
    }

    private void modifyRole(Role role, String name, String description) {
        Assert.hasText(name, "角色名不能为空或全空白字符");
        role.setName(name);
        role.setDescription(description);
    }

}
