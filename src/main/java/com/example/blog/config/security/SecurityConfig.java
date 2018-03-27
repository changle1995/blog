package com.example.blog.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.servlet.Filter;

/**
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

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }

    // 1.当定义自定义过滤器时,验证规则完全为自定义,所有调用的permitAll()方法将会失效
    // 2.由于默认的未登陆用户名为ROLE_ANONYMOUS,所以如下配置时需要在实现了GrantedAuthority的model中getAuthority方法返回的字符串所对应的数据库字段插入值为ROLE_ANONYMOUS,以及对应资源字段插入相应的可访问资源
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//配置安全策略
                .anyRequest().authenticated()//所有请求都需要验证
                .and()
                .formLogin()//使用form表单登录
                .loginPage("/login").permitAll();
        http.addFilterBefore(filter, FilterSecurityInterceptor.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

}
