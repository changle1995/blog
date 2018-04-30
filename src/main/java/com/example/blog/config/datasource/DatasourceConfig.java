package com.example.blog.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 数据源设置类
 * Author: changle
 * Date: 2018/3/13
 * Time: 7:13
 */
@Configuration
@EnableConfigurationProperties(DruidDBConfigProperties.class)
public class DatasourceConfig {

    /**
     * 使用DruidDataSource类型的数据源,拥有更多功能
     * 目前暂时没有使用额外功能
     */
    @Bean
    public DataSource dataSource(DruidDBConfigProperties druidDBConfigProperties) {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(druidDBConfigProperties.getUrl());
        datasource.setUsername(druidDBConfigProperties.getUsername());
        datasource.setPassword(druidDBConfigProperties.getPassword());
        datasource.setDriverClassName(druidDBConfigProperties.getDriverClassName());
        try {
            datasource.setFilters(druidDBConfigProperties.getFilters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }
}
