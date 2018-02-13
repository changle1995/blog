package com.example.blog.domain;

/**
 * Created with IntelliJ IDEA.
 * Author: changle
 * Date: 2018/1/2
 * Time: 20:02
 */
public class Navigation {

    private String url;
    private String name;

    public Navigation(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
