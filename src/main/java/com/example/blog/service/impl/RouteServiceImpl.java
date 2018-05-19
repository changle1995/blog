package com.example.blog.service.impl;

import com.example.blog.entity.Route;
import com.example.blog.repository.RouteRepository;
import com.example.blog.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
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
    public Route addRoute(String name, String description, String propertyName, String propertyValue) {
        Route route = routeRepository.findByNameAndPropertyName(name, propertyName);
        Assert.isNull(route, "该路由已存在");
        route = generateRoute(name, description, propertyName, propertyValue);
        return routeRepository.save(route);
    }

    @Override
    public void deleteRoute(long id) {
        Route route = routeRepository.findOne(id);
        Assert.notNull(route, "该路由不存在");
        routeRepository.delete(route);
    }

    @Override
    public Route editRoute(long id, String name, String description, String propertyName, String propertyValue) {
        Route route = routeRepository.findOne(id);
        Assert.notNull(route, "该路由不存在");
        modifyRole(route, name, description, propertyName, propertyValue);
        return routeRepository.save(route);
    }

    @Override
    public Route getRoute(long id) {
        Route route = routeRepository.findOne(id);
        Assert.notNull(route, "该路由不存在");
        return route;
    }

    @Override
    public Collection<Route> getRoutes(String name) {
        Collection<Route> routeCollection = routeRepository.findAllByName(name);
        Assert.isTrue(!CollectionUtils.isEmpty(routeCollection), "该路由不存在");
        return routeCollection;
    }

    @Override
    public Collection<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    private Route generateRoute(String name, String description, String propertyName, String propertyValue) {
        Assert.hasText(name, "路由名不能为空或全空白字符");
        Route route = new Route();
        modifyRole(route, name, description, propertyName, propertyValue);
        return route;
    }

    private void modifyRole(Route route, String name, String description, String propertyName, String propertyValue) {
        Assert.notNull(route, "路由不能为空");
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
    }

}
