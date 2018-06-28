package com.example.blog.service.auth.impl;

import com.example.blog.entity.auth.Route;
import com.example.blog.repository.auth.RouteRepository;
import com.example.blog.service.auth.RouteService;
import com.example.blog.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/5/17
 * Time: 16:37
 */
@Service
@Transactional
public class RouteServiceImpl extends BaseServiceImpl<Route> implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Override
    public Route addRoute(String name, String description, String propertyName, String propertyValue, Integer level, String parentName) {
        Assert.isNull(routeRepository.findByNameAndPropertyName(name, propertyName), "该路由已存在");
        return routeRepository.save(generateRoute(name, description, propertyName, propertyValue, level, parentName));
    }

    @Override
    public void deleteRoute(long id) {
        routeRepository.delete(id);
    }

    @Override
    public Route editRoute(long id, String name, String description, String propertyName, String propertyValue, Integer level, String parentName) {
        return routeRepository.save(modifyRoute(routeRepository.findOne(id), name, description, propertyName, propertyValue, level, parentName));
    }

    @Override
    public Route getRoute(long id) {
        return routeRepository.findOne(id);
    }

    @Override
    public Collection<Route> getRoutes(String name) {
        return routeRepository.findAllByName(name);
    }

    @Override
    public Collection<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    private Route generateRoute(String name, String description, String propertyName, String propertyValue, Integer level, String parentName) {
        Assert.hasText(name, "路由名不能为空或全空白字符");
        return modifyRoute(new Route(), name, description, propertyName, propertyValue, level, parentName);
    }

    private Route modifyRoute(Route route, String name, String description, String propertyName, String propertyValue, Integer level, String parentName) {
        Assert.notNull(route, "该路由不存在");
        if (StringUtils.hasText(name) && !name.equals(route.getName())) {
            route.setName(name);
        }
        if (StringUtils.hasText(description) && !description.equals(route.getDescription())) {
            route.setDescription(description);
        }
        if (StringUtils.hasText(propertyName) && !propertyName.equals(route.getPropertyName())) {
            route.setPropertyName(propertyName);
        }
        if (StringUtils.hasText(propertyValue) && !propertyValue.equals(route.getPropertyValue())) {
            route.setPropertyValue(propertyValue);
        }
        if (level != null && !level.equals(route.getLevel())) {
            route.setLevel(level);
        }
        if (StringUtils.hasText(parentName) && !parentName.equals(route.getParentName())) {
            route.setParentName(parentName);
        }
        return route;
    }

}
