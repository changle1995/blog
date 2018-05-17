package com.example.blog.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 控制器返回对象包装类
 * Author: changle
 * Date: 2018/4/27
 * Time: 14:42
 */
@ApiModel("包装响应码及提示消息到http请求返回")
public class RestResponse<T> {

    @JsonProperty(value = "code")
    @ApiModelProperty(value = "响应码")
    private String code;

    @JsonProperty(value = "message")
    @ApiModelProperty(value = "提示消息")
    private String message;

    @JsonProperty(value = "data")
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
