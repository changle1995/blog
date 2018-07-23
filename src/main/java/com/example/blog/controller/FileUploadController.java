package com.example.blog.controller;

import com.example.blog.config.FileUploadProperties;
import com.example.blog.domain.RestResponse;
import com.example.blog.util.FileUploadUtil;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Author: changle
 * Date: 2018/6/23
 * Time: 13:11
 */
@Api(tags = "文件控制器", description = "包含文件上传与显示两个接口")
@RestController
@RequestMapping("${controller.fileUpload.root}")
public class FileUploadController {

    @Autowired
    private FileUploadProperties fileUploadProperties;

    @Autowired
    private ResourceLoader resourceLoader;

    @ApiOperation(value = "文件上传", notes = "文件上传接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "上传的文件", required = true, dataType = "MultipartFile", paramType = "query")
    })
    @PostMapping("${controller.fileUpload.upload}")
    public RestResponse<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        return RestResponseUtil.success(FileUploadUtil.upload(file, fileUploadProperties.getBasePath()), "文件上传成功");
    }

    @ApiOperation(value = "文件显示", notes = "文件显示接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "path", value = "文件路径", required = true, paramType = "path"),
            @ApiImplicitParam(name = "filename", value = "文件名", required = true, paramType = "path")
    })
    @GetMapping("${controller.fileUpload.getFile}")
    public ResponseEntity getFile(@PathVariable String path, @PathVariable String filename) {
        return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(fileUploadProperties.getBasePath() + path, filename).toString()));
    }

}
