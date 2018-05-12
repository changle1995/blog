package com.example.blog.config.security.token;

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
@Service("customizedTokenFilterHeaderWriter")
public class CustomizedTokenFilterHeaderWriter implements Filter {

    private String urlPattern;

    private RequestMatcher requiresWriterRequestMatcher;

    public CustomizedTokenFilterHeaderWriter() {
        this.urlPattern = "/loginSuccess";
        this.requiresWriterRequestMatcher = new AntPathRequestMatcher(this.urlPattern);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (this.requiresWriter(httpServletRequest)) {
            CustomizedTokenHttpServletRequestWrapper customizedTokenHttpServletRequestWrapper = new CustomizedTokenHttpServletRequestWrapper(httpServletRequest);
            customizedTokenHttpServletRequestWrapper.putHeader("user-token", httpServletRequest.getSession(false).getId());
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

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public RequestMatcher getRequiresWriterRequestMatcher() {
        return requiresWriterRequestMatcher;
    }

    public void setRequiresWriterRequestMatcher(RequestMatcher requiresWriterRequestMatcher) {
        this.requiresWriterRequestMatcher = requiresWriterRequestMatcher;
    }

}
