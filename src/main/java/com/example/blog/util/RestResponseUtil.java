package com.example.blog.util;

import com.example.blog.domain.RestResponse;
import com.example.blog.enumeration.RestResponseEnum;

/**
 * Restful返回结果Util
 * Author: changle
 * Date: 2018/4/27
 * Time: 14:45
 */
public class RestResponseUtil {

    public static <T> RestResponse<T> success(T data) {
        RestResponse<T> result = new RestResponse<>();
        result.setCode(RestResponseEnum.CODE_SUCCESS.getCode());
        result.setMessage(RestResponseEnum.CODE_SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> RestResponse<T> success(T data, String message) {
        RestResponse<T> result = new RestResponse<>();
        result.setCode(RestResponseEnum.CODE_SUCCESS.getCode());
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static RestResponse success() {
        return success(null);
    }

    public static RestResponse error(String code, String message) {
        RestResponse result = new RestResponse();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}
