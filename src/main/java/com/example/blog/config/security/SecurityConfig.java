package com.example.blog.config.security;

import com.example.blog.entity.auth.Permission;
import com.example.blog.service.auth.RoleService;
import com.example.blog.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Qualifier("customizedCorsConfigurationSource")
    private CorsConfigurationSource corsConfigurationSource;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SecurityConfigProperties securityConfigProperties;

    /**
     * 此方法来配置各个属性
     * 此配置仅限制spring security自带的过滤器,对自定义过滤器无效
     * 由于默认的未登陆用户名为ROLE_ANONYMOUS
     * 所以如下配置时需要在实现了GrantedAuthority的model中getAuthority方法返回的字符串所对应的数据库字段插入值为ROLE_ANONYMOUS,以及对应资源字段插入相应的可访问资源
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置cors跨域资源共享的配置
        http.cors().configurationSource(this.corsConfigurationSource);
        //关闭csrf
        http.csrf().disable();
        //配置默认权限过滤器FilterSecurityInterceptor的安全策略
        //设置匿名权限url不需要拦截其余都需要验证权限
        Set<String> anonymousAllowUrl = roleService.getRole("ROLE_ANONYMOUS").getPermissionSet().stream().map(Permission::getUrl).collect(Collectors.toSet());
        http.authorizeRequests().antMatchers(anonymousAllowUrl.toArray(new String[anonymousAllowUrl.size()])).permitAll().anyRequest().authenticated();
//        http.authorizeRequests().anyRequest().authenticated();
//        http.anonymous().principal(userService.getUser("anonymous")).authorities(new ArrayList<>(userService.getUser("anonymous").getRoleSet()));
        //使用默认的form表单登录,验证成功则跳转到/loginSuccess,验证失败则跳转到/loginFailure
        http.formLogin().successForwardUrl(this.securityConfigProperties.getSuccessForwardUrl()).failureForwardUrl(this.securityConfigProperties.getFailureForwardUrl());
        //使用默认的退出,退出成功则跳转到/logoutSuccess
        http.logout().logoutSuccessUrl(this.securityConfigProperties.getLogoutSuccessUrl());
        //设置未登录而跳转访问的页面,设置无权限而跳转访问的页面
        http.exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(this.securityConfigProperties.getAuthenticationEntryPointUrl())).accessDeniedPage(this.securityConfigProperties.getAccessDeniedUrl());
        //设置同一用户最大登录数,并设置后登录者踢掉先登录者
        //因为这样设置仍然无效,暂时先不管session
        //http.sessionManagement().maximumSessions(this.securityConfigProperties.getMaximumSessions()).maxSessionsPreventsLogin(this.securityConfigProperties.isMaxSessionsPreventsLogin());
    }

    /**
     * 在此注入自定义的UserDetailsService
     * 并且设置加密方式
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
