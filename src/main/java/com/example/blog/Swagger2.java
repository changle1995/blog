package com.example.blog;

import com.example.blog.enumeration.HeaderNameEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: changle
 * Date: 2018/3/15
 * Time: 14:21
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createAuthRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .groupName("权限")
                .ignoredParameterTypes(HttpServletRequest.class, HttpServletResponse.class)
                .globalOperationParameters(buildOperationParameters())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.blog.controller.auth"))
                .paths(PathSelectors.any()).build();
    }

    @Bean
    public Docket createBlogRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .groupName("博客")
                .ignoredParameterTypes(HttpServletRequest.class, HttpServletResponse.class)
                .globalOperationParameters(buildOperationParameters())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.blog.controller.blog"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        String title = "博客";
        String description = "我的博客项目";
        Contact contact = new Contact("常乐", "https://github.com/changle1995", "634692344@qq.com");
        String version = "1.0";
        return new ApiInfoBuilder().title(title).description(description).contact(contact).version(version).build();
    }

    private List<Parameter> buildOperationParameters() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        Parameter tokenParam = parameterBuilder.name(HeaderNameEnum.USER_TOKEN.getName())
                .description("登录后验证用的token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true).build();
        List<Parameter> parameterList = new ArrayList<>();
        parameterList.add(tokenParam);
        return parameterList;
    }

}
