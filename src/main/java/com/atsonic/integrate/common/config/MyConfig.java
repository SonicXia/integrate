package com.atsonic.integrate.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

//@PropertySource(value = "{classpath:application-dev.properties}")
@Component
@ConfigurationProperties(prefix = "globle-config")
public class MyConfig {

    /**
     * shiro中sessin超时时间
     */
    private Integer sessionTimeOut;

    /**
     * 客户账号、代理商账号默认初始密码
     */
    private String initialPsw;

    /**
     * 系统初始密码
     */
    private String systemInitialPwd;

    /**
     * 资质认证图片上传地址
     */
    private String certificationUrl;


    /**
     * 文件下载路径
     */
    private String downloadUrl;

    /**
     * 支持的图片上传格式
     */
    private String picFormat;

    /**
     * 在线检测号码保存路径
     */
    private String onlineResultPath;

    public Integer getSessionTimeOut() {
        return sessionTimeOut;
    }

    public void setSessionTimeOut(Integer sessionTimeOut) {
        this.sessionTimeOut = sessionTimeOut;
    }

    public String getInitialPsw() {
        return initialPsw;
    }

    public void setInitialPsw(String initialPsw) {
        this.initialPsw = initialPsw;
    }

    public String getSystemInitialPwd() {
        return systemInitialPwd;
    }

    public void setSystemInitialPwd(String systemInitialPwd) {
        this.systemInitialPwd = systemInitialPwd;
    }

    public String getCertificationUrl() {
        return certificationUrl;
    }

    public void setCertificationUrl(String certificationUrl) {
        this.certificationUrl = certificationUrl;
    }

    public String getPicFormat() {
        return picFormat;
    }

    public void setPicFormat(String picFormat) {
        this.picFormat = picFormat;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
        File path = new File(downloadUrl);
        if(!path.exists()){
            path.mkdirs();
        }
    }

    public String getOnlineResultPath() {
        return onlineResultPath;
    }

    public void setOnlineResultPath(String onlineResultPath) {
        this.onlineResultPath = onlineResultPath;
    }
}
