package com.example.blog.repository.auth;

import com.example.blog.entity.auth.Route;
import com.example.blog.repository.BaseRepository;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/5/17
 * Time: 16:23
 */
public interface RouteRepository extends BaseRepository<Route, Long> {

    Route findByNameAndPropertyName(String name, String propertyName);

    Collection<Route> findAllByName(String name);

    Collection<Route> findAllByIdIn(Collection<Long> idCollection);

}
