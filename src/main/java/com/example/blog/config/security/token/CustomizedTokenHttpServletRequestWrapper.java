package com.example.blog.config.security.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * 自定义HttpServletRequest包装类,用于修改HttpServletRequest的头部信息
 * Author: changle
 * Date: 2018/5/12
 * Time: 12:35
 */
public class CustomizedTokenHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private Map<String, String> customizedHeaders;

    public CustomizedTokenHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.customizedHeaders = new HashMap<>();
    }

    public void putHeader(String key, String value) {
        this.customizedHeaders.put(key, value);
    }

    public String getHeader(String key) {
        String value = this.customizedHeaders.get(key);
        if (value != null) {
            return value;
        }
        return ((HttpServletRequest) getRequest()).getHeader(key);
    }

    public Enumeration<String> getHeaderNames() {
        Set<String> set = new HashSet<>(customizedHeaders.keySet());
        Enumeration<String> enumeration = ((HttpServletRequest) getRequest()).getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            set.add(key);
        }
        return Collections.enumeration(set);
    }

}
