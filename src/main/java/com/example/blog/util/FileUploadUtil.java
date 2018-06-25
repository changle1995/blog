package com.example.blog.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Random;

/**
 * 文件上传工具类
 * Author: changle
 * Date: 2018/6/23
 * Time: 12:37
 */
public class FileUploadUtil {

    public static String upload(MultipartFile multipartFile, String basePath) throws IOException {
        String location = DateFormatUtils.format(new Date(), "yyyyMMdd") + "/";
        String filename = multipartFile.getOriginalFilename();
        String targetFilename = filename.substring(0, filename.lastIndexOf(".")) + new Random().nextInt(100000000) + filename.substring(filename.lastIndexOf("."));
        File targetFileDir = new File(basePath + location);
        if (!targetFileDir.exists()) {
            targetFileDir.mkdirs();
        }
        Files.copy(multipartFile.getInputStream(), Paths.get(basePath + location, targetFilename), StandardCopyOption.REPLACE_EXISTING);
        return location + targetFilename;
    }

}
