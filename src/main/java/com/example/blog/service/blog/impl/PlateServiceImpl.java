package com.example.blog.service.blog.impl;

import com.example.blog.entity.blog.Plate;
import com.example.blog.repository.blog.PlateRepository;
import com.example.blog.service.blog.PlateService;
import com.example.blog.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 17:28
 */
@Service
@Transactional
public class PlateServiceImpl extends BaseServiceImpl<Plate> implements PlateService {

    @Autowired
    private PlateRepository plateRepository;

    @Override
    public Plate addPlate(String name, String description, int state) {
        Assert.isNull(plateRepository.findByName(name), "该板块名已存在");
        return plateRepository.save(modifyPlate(new Plate(), name, description, state));
    }

    @Override
    public void deletePlate(long id) {
        plateRepository.delete(id);
    }

    @Override
    public Plate editPlate(long id, String name, String description, int state) {
        Plate plate = plateRepository.findByName(name);
        Assert.state(plate == null || plate.getId() == id, "该板块名已存在");
        return plateRepository.save(modifyPlate(plateRepository.findOne(id), name, description, state));
    }

    @Override
    public Collection<Plate> getAllPlates() {
        return plateRepository.findAll(new Sort("id"));
    }

    private Plate modifyPlate(Plate plate, String name, String description, Integer state) {
        Assert.notNull(plate, "该板块不存在");
        Assert.hasText(name, "板块名不能为空或全空白字符");
        plate.setName(name);
        plate.setDescription(description);
        plate.setState(state);
        return plate;
    }

}
