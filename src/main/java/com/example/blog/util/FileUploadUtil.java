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
        String filename = multipartFile.getOriginalFilename();
        String name = filename.substring(0, filename.lastIndexOf("."));
        String type = filename.substring(filename.lastIndexOf(".") + 1);
        String location = File.separator + DateFormatUtils.format(new Date(), "yyyyMMdd") + File.separator + type + File.separator;
        String targetFilename = name + new Random().nextInt(100000000) + "." + type;
        File targetFileDir = new File(basePath + location);
        if (!targetFileDir.exists()) {
            targetFileDir.mkdirs();
        }
        Files.copy(multipartFile.getInputStream(), Paths.get(basePath + location, targetFilename), StandardCopyOption.REPLACE_EXISTING);
        return location + targetFilename;
    }

}
