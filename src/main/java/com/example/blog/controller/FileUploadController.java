package com.example.blog.controller;

import com.example.blog.config.FileUploadProperties;
import com.example.blog.domain.RestResponse;
import com.example.blog.util.FileUploadUtil;
import com.example.blog.util.RestResponseUtil;
import io.swagger.annotations.*;
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
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private FileUploadProperties fileUploadProperties;

    @Autowired
    private ResourceLoader resourceLoader;

    @ApiOperation(value = "文件上传", notes = "文件上传接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "上传的文件", required = true, dataType = "MultipartFile", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "上传成功")
    })
    @PostMapping("/upload")
    public RestResponse<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        return RestResponseUtil.success(FileUploadUtil.upload(file, fileUploadProperties.getBasePath()), "文件上传成功");
    }

    @ApiOperation(value = "文件显示", notes = "文件显示接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "path", value = "文件路径", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "filename", value = "文件名", required = true, dataType = "String", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "获取成功")
    })
    @GetMapping("/{path}/{filename:.+}")
    public ResponseEntity getFile(@PathVariable String path, @PathVariable String filename) {
        return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(fileUploadProperties.getBasePath() + path, filename).toString()));
    }

}