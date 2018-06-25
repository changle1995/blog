package com.example.blog.service.blog;

import com.example.blog.entity.blog.Plate;
import com.example.blog.service.BaseService;

import java.util.Collection;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 17:24
 */
public interface PlateService extends BaseService<Plate> {

    /**
     * 新增板块方法
     *
     * @param name        板块名称
     * @param description 板块描述
     * @param state       板块状态
     * @return 返回新增的板块
     */
    Plate addPlate(String name, String description, Integer state);

    /**
     * 删除板块方法
     *
     * @param id 板块ID
     */
    void deletePlate(long id);

    /**
     * 修改板块方法
     *
     * @param id          板块ID
     * @param name        板块名称
     * @param description 板块描述
     * @param state       板块状态
     * @return 返回修改后的板块
     */
    Plate editPlate(long id, String name, String description, Integer state);

    /**
     * 根据板块ID查找板块方法
     *
     * @param id 板块ID
     * @return 返回获取的板块
     */
    Plate getPlate(long id);

    /**
     * 根据板块名称查找板块方法
     *
     * @param name 板块名称
     * @return 返回获取的板块
     */
    Plate getPlate(String name);

    /**
     * 查找所有板块
     *
     * @return 返回所有板块
     */
    Collection<Plate> getAllPlates();

}
