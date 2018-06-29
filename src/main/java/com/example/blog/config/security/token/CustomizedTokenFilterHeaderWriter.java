package com.example.blog.config.security.token;

import com.example.blog.enumeration.HeaderNameEnum;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 登录成功自动将sessionId写入请求头部,为后续token验证做准备
 * Author: changle
 * Date: 2018/5/12
 * Time: 14:12
 */
@Order(1000)
@Service("customizedTokenFilterHeaderWriter")
@EnableConfigurationProperties(TokenConfigProperties.class)
public class CustomizedTokenFilterHeaderWriter implements Filter {

    private RequestMatcher requiresWriterRequestMatcher;

    public CustomizedTokenFilterHeaderWriter(TokenConfigProperties tokenConfigProperties) {
        this.requiresWriterRequestMatcher = new AntPathRequestMatcher(tokenConfigProperties.getWriterRequestUrl());
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (this.requiresWriter(httpServletRequest)) {
            CustomizedTokenHttpServletRequestWrapper customizedTokenHttpServletRequestWrapper = new CustomizedTokenHttpServletRequestWrapper(httpServletRequest);
            customizedTokenHttpServletRequestWrapper.putHeader(HeaderNameEnum.USER_TOKEN.getName(), httpServletRequest.getSession(false).getId());
            filterChain.doFilter(customizedTokenHttpServletRequestWrapper, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }

    protected boolean requiresWriter(HttpServletRequest request) {
        return this.requiresWriterRequestMatcher.matches(request);
    }

    public RequestMatcher getRequiresWriterRequestMatcher() {
        return requiresWriterRequestMatcher;
    }

    public void setRequiresWriterRequestMatcher(RequestMatcher requiresWriterRequestMatcher) {
        this.requiresWriterRequestMatcher = requiresWriterRequestMatcher;
    }

}
