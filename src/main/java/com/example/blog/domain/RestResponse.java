package com.example.blog.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Author: changle
 * Date: 2018/4/27
 * Time: 14:42
 */
@ApiModel("包装响应码及提示消息到http请求返回")
public class RestResponse<T> {

    @ApiModelProperty(value = "响应码")
    private String code;

    @ApiModelProperty(value = "提示消息")
    private String message;

    @ApiModelProperty(value = "具体的内容")
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
