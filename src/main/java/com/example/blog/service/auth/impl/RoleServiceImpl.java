package com.example.blog.service.auth.impl;

import com.example.blog.entity.auth.Role;
import com.example.blog.repository.auth.RoleRepository;
import com.example.blog.service.auth.RoleService;
import com.example.blog.service.impl.BaseServiceImpl;
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
        Assert.isNull(roleRepository.findByName(name), "该角色已存在");
        return roleRepository.save(modifyRole(new Role(), name, description));
    }

    @Override
    public void deleteRole(long id) {
        roleRepository.delete(id);
    }

    @Override
    public Role editRole(long id, String name, String description) {
        return roleRepository.save(modifyRole(roleRepository.findOne(id), name, description));
    }

    @Override
    public Role getRole(long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public Role getRole(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Collection<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    private Role modifyRole(Role role, String name, String description) {
        Assert.notNull(role, "该角色不存在");
        Assert.hasText(name, "角色名不能为空或全空白字符");
        role.setName(name);
        role.setDescription(description);
        return role;
    }

}
