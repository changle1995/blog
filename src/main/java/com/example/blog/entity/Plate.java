package com.example.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: changle
 * Date: 2018/3/15
 * Time: 22:27
 */
@Entity
@Table(name = "plate")
public class Plate extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
