package com.example.blog.service;

import com.example.blog.entity.BaseEntity;

/**
 * Author: changle
 * Date: 2018/5/5
 * Time: 12:32
 */
public interface BaseService<T extends BaseEntity> {

    /**
     * 新增实体方法
     *
     * @param entity 要新增的实体
     * @return 返回新增的实体
     */
    T add(T entity);

    /**
     * 删除实体方法
     *
     * @param id 要删除实体的主键ID
     * @return 无返回值
     */
    void delete(long id);

    /**
     * 更新实体方法
     *
     * @param entity 要更新的实体
     * @return 返回更新的实体
     */
    T update(T entity);

    /**
     * 获取实体方法
     *
     * @param id 要查找的实体ID
     * @return 返回找到的实体
     */
    T get(long id);

}
