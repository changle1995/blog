package com.example.blog.enumeration;

/**
 * 枚举,响应码描述
 * Author: changle
 * Date: 2018/4/27
 * Time: 14:57
 */
public enum RestResponseEnum {

    CODE_SUCCESS("200", "成功"),
    CODE_ERROR_UNKNOWN("400", "服务器不理解请求的语法"),
    CODE_ERROR_LOGIN_FAILURE("401.1", "登录失败,账号或密码错误"),
    CODE_ERROR_UNAUTHORIZED("401.2", "未登录,当前无操作权限"),
    CODE_ERROR_ACCESS_DENIED("403", "已登录,当前无操作权限"),
    CODE_ERROR_HTTP("404", "服务器找不到请求的链接");

    private String code;

    private String message;

    RestResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
