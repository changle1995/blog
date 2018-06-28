package com.example.blog.service.auth.impl;

import com.example.blog.entity.auth.Role;
import com.example.blog.repository.auth.RoleRepository;
import com.example.blog.service.auth.RoleService;
import com.example.blog.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

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
        return roleRepository.save(generateRole(name, description));
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

    private Role generateRole(String name, String description) {
        Assert.hasText(name, "角色名不能为空或全空白字符");
        return modifyRole(new Role(), name, description);
    }

    private Role modifyRole(Role role, String name, String description) {
        Assert.notNull(role, "该角色不存在");
        if (StringUtils.hasText(name) && !name.equals(role.getName())) {
            role.setName(name);
        }
        if (StringUtils.hasText(description) && !description.equals(role.getDescription())) {
            role.setDescription(description);
        }
        return role;
    }

}
