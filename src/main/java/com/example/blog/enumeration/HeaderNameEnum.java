package com.example.blog.enumeration;

/**
 * Author: changle
 * Date: 2018/5/14
 * Time: 16:17
 */
public enum HeaderNameEnum {

    USER_TOKEN("user-token");

    private String name;

    HeaderNameEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
