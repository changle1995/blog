package com.example.blog.service;

import com.example.blog.entity.Plate;
import com.example.blog.repository.PlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/16
 * Time: 0:05
 */
@Service
@Transactional
public class PlateService extends BaseService<Plate> {

    @Autowired
    PlatRepository platRepository;

    public Plate save(String name, String description) {
        Plate plate = new Plate();
        plate.setName(name);
        plate.setDescription(description);
        return add(plate);
    }

    public Plate update(long id, String name, String description) {
        Plate plate = new Plate();
        plate.setId(id);
        plate.setName(name);
        plate.setDescription(description);
        return update(plate);
    }

    public Plate findOne(Long id) {
        return platRepository.findOne(id);
    }

    public List<Plate> findAll() {
        return platRepository.findAll();
    }

}
