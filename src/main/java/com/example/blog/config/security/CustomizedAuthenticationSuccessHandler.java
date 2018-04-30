package com.example.blog.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义验证成功后处理器
 * Author: changle
 * Date: 2018/4/28
 * Time: 11:28
 */
@Service("customizedAuthenticationSuccessHandler")
public class CustomizedAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private String defaultSuccessUrl = "/loginSuccess";

    /**
     * 每次验证成功后将调用此方法,目前暂时设置为forward到defaultSuccessUrl
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletRequest.getRequestDispatcher(defaultSuccessUrl).forward(httpServletRequest, httpServletResponse);
    }

    public String getDefaultSuccessUrl() {
        return defaultSuccessUrl;
    }

    public void setDefaultSuccessUrl(String defaultSuccessUrl) {
        this.defaultSuccessUrl = defaultSuccessUrl;
    }
}
