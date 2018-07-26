package com.example.blog.service.auth;

import com.example.blog.entity.auth.Route;
import com.example.blog.service.BaseService;
import org.springframework.data.domain.Page;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/5/17
 * Time: 16:24
 */
public interface RouteService extends BaseService<Route> {

    /**
     * 新增路由方法
     *
     * @param name          路由名称
     * @param description   路由描述
     * @param propertyName  参数名称
     * @param propertyValue 参数值
     * @param level         路由级别
     * @param parentName    父路由名称
     * @return 返回新增的路由
     */
    Route addRoute(String name, String description, String propertyName, String propertyValue, Integer level, String parentName);

    /**
     * 删除路由方法
     *
     * @param id 路由主键ID
     */
    void deleteRoute(long id);

    /**
     * 修改路由方法
     *
     * @param id            路由主键ID
     * @param name          路由名称
     * @param description   路由描述
     * @param propertyName  参数名称
     * @param propertyValue 参数值
     * @param level         路由级别
     * @param parentName    父路由名称
     * @return 返回修改后的路由
     */
    Route editRoute(long id, String name, String description, String propertyName, String propertyValue, Integer level, String parentName);

    /**
     * 根据路由名称查找路由方法
     *
     * @param name 路由名称
     * @return 返回找到的路由
     */
    Collection<Route> getRoutes(String name);

    /**
     * 分页查找所有路由方法
     *
     * @return 返回所有的路由
     */
    Page<Route> getRoutes(Integer pageNumber, Integer pageSize);

}
