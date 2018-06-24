package com.example.blog.service.auth.impl;

import com.example.blog.entity.auth.Role;
import com.example.blog.repository.auth.RoleRepository;
import com.example.blog.repository.auth.RouteRepository;
import com.example.blog.service.auth.RoleRouteService;
import com.example.blog.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/5/18
 * Time: 22:43
 */
@Service
@Transactional
public class RoleRouteServiceImpl extends BaseServiceImpl<Role> implements RoleRouteService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Role addRoutesToRole(long roleId, Collection<Long> routeIdCollection) {
        Role role = roleRepository.findOne(roleId);
        Assert.notNull(role, "角色不存在");
        role.getRouteSet().addAll(routeRepository.findAllByIdIn(routeIdCollection));
        return roleRepository.save(role);
    }

    @Override
    public Role deleteRoutesOfRole(long roleId, Collection<Long> routeIdCollection) {
        Role role = roleRepository.findOne(roleId);
        Assert.notNull(role, "角色不存在");
        role.getRouteSet().removeAll(routeRepository.findAllByIdIn(routeIdCollection));
        return roleRepository.save(role);
    }

}
