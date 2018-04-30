package com.example.blog.service;

import com.example.blog.entity.Role;
import com.example.blog.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/20
 * Time: 23:42
 */
@Service
@Transactional
public class RoleService extends BaseService<Role> {

    @Autowired
    private RoleRepository roleRepository;

    public Role addRole(String name, String description) {
        Role role = roleRepository.findByName(name);
        Assert.isNull(role, "该角色名已存在");
        role = generateRole(name, description);
        return roleRepository.save(role);
    }

    public void deleteRole(long id) {
        Role role = roleRepository.findOne(id);
        Assert.notNull(role, "该角色不存在");
        roleRepository.delete(role);
    }

    public Role editRole(long id, String name, String description) {
        Role role = roleRepository.findOne(id);
        Assert.notNull(role, "该角色不存在");
        modifyRole(role, name, description);
        return roleRepository.save(role);
    }

    public Role getRole(Long id) {
        Role role = roleRepository.findOne(id);
        Assert.notNull(role, "该角色不存在");
        return role;
    }

    public Role getRole(String name) {
        Role role = roleRepository.findByName(name);
        Assert.notNull(role, "该角色名不存在");
        return role;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role generateRole(String name, String description) {
        Role role = new Role();
        modifyRole(role, name, description);
        return role;
    }

    public void modifyRole(Role role, String name, String description) {
        Assert.notNull(role, "待修改的角色不能为空");
        Assert.hasText(name, "角色名不能为空或全空白字符");
        role.setName(name);
        role.setDescription(description);
    }

}
