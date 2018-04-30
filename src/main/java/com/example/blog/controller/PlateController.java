package com.example.blog.controller;

import com.example.blog.entity.Plate;
import com.example.blog.service.PlateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/16
 * Time: 11:52
 */
@RestController
@RequestMapping("/plate")
public class PlateController {

    @Autowired
    private PlateService plateService;

    @GetMapping("/add/{name}/{description}")
    public Plate addPlate(@PathVariable String name, @PathVariable String description) {
        return plateService.save(name, description);
    }

    @GetMapping("/delete/{id}")
    public void deletePlate(@PathVariable long id) {
        plateService.delete(id);
    }

    @GetMapping("/update/{id}/{name}/{description}")
    public Plate updatePlate(@PathVariable long id, @PathVariable String name, @PathVariable String description) {
        return plateService.update(id, name, description);
    }

    @GetMapping("/get/{id}")
    public Plate getPlate(@PathVariable Long id) {
        return plateService.findOne(id);
    }

    @GetMapping("/get/list")
    public List<Plate> getPlateList() {
        return plateService.findAll();
    }
}
