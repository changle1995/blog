package com.example.blog.service;

import com.example.blog.entity.BaseEntity;
import com.example.blog.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * Author: changle
 * Date: 2018/3/16
 * Time: 11:37
 */
public abstract class BaseService<T extends BaseEntity> {

    @Autowired
    private BaseRepository<T, Long> baseRepository;

    public T add(T entity) {
        Assert.notNull(entity, "传入的参数不能为空");
        return baseRepository.save(entity);
    }

    public void delete(Long id) {
        T entity = baseRepository.findOne(id);
        if (entity != null) {
            baseRepository.delete(entity);
        }
    }

    public T update(T entity) {
        Assert.notNull(entity, "传入的参数不能为空");
        return baseRepository.save(entity);
    }
}
