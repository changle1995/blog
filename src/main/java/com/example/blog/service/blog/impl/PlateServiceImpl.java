package com.example.blog.service.blog.impl;

import com.example.blog.entity.blog.Plate;
import com.example.blog.repository.blog.PlateRepository;
import com.example.blog.service.blog.PlateService;
import com.example.blog.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Plate addPlate(String name, String description, Integer state) {
        Plate plate = plateRepository.findByName(name);
        Assert.isNull(plate, "该板块已存在");
        plate = generatePlate(name, description, state);
        return plateRepository.save(plate);
    }

    @Override
    public void deletePlate(long id) {
        Plate plate = plateRepository.findOne(id);
        Assert.notNull(plate, "该板块不存在");
        plateRepository.delete(plate);
    }

    @Override
    public Plate editPlate(long id, String name, String description, Integer state) {
        Plate plate = plateRepository.findOne(id);
        Assert.notNull(plate, "该板块不存在");
        modifyPlate(plate, name, description, state);
        return plateRepository.save(plate);
    }

    @Override
    public Plate getPlate(long id) {
        Plate plate = plateRepository.findOne(id);
        Assert.notNull(plate, "该板块不存在");
        return plate;
    }

    @Override
    public Plate getPlate(String name) {
        Plate plate = plateRepository.findByName(name);
        Assert.notNull(plate, "该板块不存在");
        return plate;
    }

    @Override
    public Collection<Plate> getAllPlates() {
        return plateRepository.findAll();
    }

    private Plate generatePlate(String name, String description, Integer state) {
        Plate plate = new Plate();
        modifyPlate(plate, name, description, state);
        return plate;
    }

    private void modifyPlate(Plate plate, String name, String description, Integer state) {
        Assert.notNull(plate, "板块不能为空");
        Assert.hasText(name, "板块名不能为空或全空白字符");
        plate.setName(name);
        plate.setDescription(description);
        plate.setState(state);
    }

}
