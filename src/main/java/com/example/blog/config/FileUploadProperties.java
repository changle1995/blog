package com.example.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Author: changle
 * Date: 2018/6/23
 * Time: 12:18
 */
@Component
@ConfigurationProperties("file.upload")
public class FileUploadProperties {

    private Map<String, String> localtion;

    public Map<String, String> getLocaltion() {
        return localtion;
    }

    public void setLocaltion(Map<String, String> localtion) {
        this.localtion = localtion;
    }

    public String getBasePath() {
        String location;
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")) {
            location = this.getLocaltion().get("windows");
        } else {
            location = this.getLocaltion().get("linux");
        }
        return location;
    }

}
