package com.example.blog.exception;

import com.example.blog.domain.RestResponse;
import com.example.blog.util.RestResponseUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * Author: changle
 * Date: 2018/6/29
 * Time: 16:24
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    //指定处理何种异常
    @ExceptionHandler(value = RuntimeException.class)
    public RestResponse errorHandler(RuntimeException runtimeException) {
        return RestResponseUtil.error("400", runtimeException.getMessage());
    }

}
