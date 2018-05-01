package com.example.blog.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.Filter;

/**
 * spring security配置类
 * Author: changle
 * Date: 2018/3/21
 * Time: 0:06
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customizedUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("customizedFilterSecurityInterceptor")
    private Filter filter;

    @Autowired
    @Qualifier("customizedAuthenticationSuccessHandler")
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * 使用spring security提供的DaoAuthenticationProvider
     * 但注入自定义的UserDetailsService,并且在此添加密码加密方式
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }

    /**
     * 此方法来配置各个属性
     * 此配置仅限制spring security自带的过滤器,对自定义过滤器无效
     * 由于默认的未登陆用户名为ROLE_ANONYMOUS
     * 所以如下配置时需要在实现了GrantedAuthority的model中getAuthority方法返回的字符串所对应的数据库字段插入值为ROLE_ANONYMOUS,以及对应资源字段插入相应的可访问资源
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()//配置安全策略
                .antMatchers("/login").permitAll()
                .antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()
                .anyRequest().authenticated()//所有请求都需要验证
                .and()
                .formLogin().successHandler(authenticationSuccessHandler);//使用form表单登录
//                .loginPage("/login"); 定义登录页面的url,不定义则使用spring自带的默认登录界面
        http.addFilterBefore(filter, FilterSecurityInterceptor.class);
    }

    /**
     * 在此注入前面的AuthenticationProvider
     * 在此注入自定义的UserDetailsService
     * AuthenticationProvider中的虽然已经注入过UserDetailsService,但不会自动应用到过滤器的UserDetailsService中
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

}
