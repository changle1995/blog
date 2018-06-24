package com.example.blog.service.blog.impl;

import com.example.blog.entity.blog.Plate;
import com.example.blog.service.blog.PlateService;
import com.example.blog.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: changle
 * Date: 2018/6/24
 * Time: 17:28
 */
@Service
@Transactional
public class PlateServiceImpl extends BaseServiceImpl<Plate> implements PlateService {
}
