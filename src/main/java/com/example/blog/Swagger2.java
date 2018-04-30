package com.example.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Author: changle
 * Date: 2018/3/15
 * Time: 14:21
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.example.blog"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        String title = "博客";
        String description = "我的博客项目";
        Contact contact = new Contact("常乐", "https://github.com/changle1995", "634692344@qq.com");
        String version = "1.0";
        return new ApiInfoBuilder().title(title).description(description).contact(contact).version(version).build();
    }
}
