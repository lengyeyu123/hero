package com.han.hero.framework.config.properties;

import cn.hutool.core.io.FileUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "hero")
public class HeroProperties {

    private String uploadFilePathWinPrefix;

    private String uploadFilePathLinuxPrefix;

    private String downloadFilePathWinPrefix;

    private String downloadFilePathLinuxPrefix;

    public String getUploadFilePathWinPrefix() {
        return uploadFilePathWinPrefix;
    }

    public void setUploadFilePathWinPrefix(String uploadFilePathWinPrefix) {
        this.uploadFilePathWinPrefix = uploadFilePathWinPrefix;
    }

    public String getUploadFilePathLinuxPrefix() {
        return uploadFilePathLinuxPrefix;
    }

    public void setUploadFilePathLinuxPrefix(String uploadFilePathLinuxPrefix) {
        this.uploadFilePathLinuxPrefix = uploadFilePathLinuxPrefix;
    }

    public String getDownloadFilePathWinPrefix() {
        return downloadFilePathWinPrefix;
    }

    public void setDownloadFilePathWinPrefix(String downloadFilePathWinPrefix) {
        this.downloadFilePathWinPrefix = downloadFilePathWinPrefix;
    }

    public String getDownloadFilePathLinuxPrefix() {
        return downloadFilePathLinuxPrefix;
    }

    public void setDownloadFilePathLinuxPrefix(String downloadFilePathLinuxPrefix) {
        this.downloadFilePathLinuxPrefix = downloadFilePathLinuxPrefix;
    }

    public String getUploadFilePathPrefix() {
        return FileUtil.isWindows() ? uploadFilePathWinPrefix : uploadFilePathLinuxPrefix;
    }

    public String getDownloadFilePathPrefix() {
        return FileUtil.isWindows() ? downloadFilePathWinPrefix : downloadFilePathLinuxPrefix;
    }

}
