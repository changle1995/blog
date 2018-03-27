package com.example.blog.service;

import com.example.blog.domain.BaseEntity;
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

    public T addOrUpdate(T entity) {
        Assert.notNull(entity, "The entity must be not null");
        return baseRepository.save(entity);
    }

    public void delete(Long id) {
        T entity = baseRepository.findOne(id);
        if (entity != null) {
            baseRepository.delete(entity);
        }
    }
}
