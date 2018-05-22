package com.example.blog.config.security.cors;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 生成跨域配置的CorsConfigurationSource
 * Author: changle
 * Date: 2018/5/22
 * Time: 8:57
 */
@Configuration
@EnableConfigurationProperties(CorsConfigProperties.class)
public class CorsConfig {

    @Bean("customizedCorsConfigurationSource")
    public CorsConfigurationSource buildConfig(CorsConfigProperties corsConfigProperties) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(corsConfigProperties.getAllowedOrigins());
        corsConfiguration.setAllowCredentials(corsConfigProperties.getAllowCredentials());
        corsConfiguration.setAllowedMethods(corsConfigProperties.getAllowedHeaders());
        corsConfiguration.setAllowedMethods(corsConfigProperties.getAllowedMethods());
        corsConfiguration.setMaxAge(corsConfigProperties.getMaxAge());
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}
