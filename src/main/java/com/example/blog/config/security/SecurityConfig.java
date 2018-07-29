package com.example.blog.config.security;

import com.example.blog.entity.auth.Permission;
import com.example.blog.entity.auth.Role;
import com.example.blog.entity.auth.User;
import com.example.blog.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.util.StringUtils;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.ArrayList;

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
    private SecurityConfigProperties securityConfigProperties;

    /**
     * 此方法来配置spring security各个属性
     * 此配置仅限制spring security自带的过滤器,对自定义过滤器无效
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //设置cors跨域资源共享的配置
        http.cors().configurationSource(this.corsConfigurationSource);
        //关闭csrf
        http.csrf().disable();
        //配置默认权限过滤器FilterSecurityInterceptor的安全策略
        //设置匿名者的用户与拥有的权限(角色)
        User user = userService.getUser(this.securityConfigProperties.getAnonymousUsername());
        http.anonymous().principal(user).authorities(new ArrayList<>(user.getRoleSet()));
        //设置匿名用户能够访问的URL
        for (Role role : user.getRoleSet()) {
            for (Permission permission : role.getPermissionSet()) {
                String[] methods = StringUtils.hasText(permission.getMethod()) && !permission.getMethod().equals("*") ? permission.getMethod().split(",") : new String[]{"*"};
                for (String method : methods) {
                    HttpMethod httpMethod = !method.equals("*") ? HttpMethod.valueOf(method.toUpperCase()) : null;
                    http.authorizeRequests().antMatchers(httpMethod, permission.getUrl()).hasAuthority(role.getAuthority());
                }
            }
        }
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
