package com.example.blog.service.impl;

import com.example.blog.entity.BaseEntity;
import com.example.blog.repository.BaseRepository;
import com.example.blog.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Author: changle
 * Date: 2018/3/16
 * Time: 11:37
 */
@Transactional
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    @Autowired
    private BaseRepository<T, Long> baseRepository;

    @Override
    public T add(T entity) {
        Assert.notNull(entity, "传入的参数不能为空");
        return baseRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        T entity = baseRepository.findOne(id);
        if (entity != null) {
            baseRepository.delete(entity);
        }
    }

    @Override
    public T update(T entity) {
        Assert.notNull(entity, "传入的参数不能为空");
        return baseRepository.save(entity);
    }

    @Override
    public T get(long id) {
        return baseRepository.findOne(id);
    }

}
