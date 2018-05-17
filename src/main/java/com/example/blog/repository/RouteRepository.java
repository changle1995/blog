package com.example.blog.repository;

import com.example.blog.entity.Route;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/5/17
 * Time: 16:23
 */
public interface RouteRepository extends BaseRepository<Route, Long> {

    Route findByNameAndPropertyName(String name, String propertyName);

    Collection<Route> findByName(String name);

}
