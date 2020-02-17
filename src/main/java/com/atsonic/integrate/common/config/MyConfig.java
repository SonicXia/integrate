package com.atsonic.integrate.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

//@PropertySource(value = "{classpath:application-dev.properties}")
@Component
@ConfigurationProperties(prefix = "globle-config")
public class MyConfig {

    private String redisPrefix;

    private String jwtSalt;

    private String ipWhitelist;

    public String getRedisPrefix() {
        return redisPrefix;
    }

    public void setRedisPrefix(String redisPrefix) {
        this.redisPrefix = redisPrefix;
    }

    public String getJwtSalt() {
        return jwtSalt;
    }

    public void setJwtSalt(String jwtSalt) {
        this.jwtSalt = jwtSalt;
    }

    public String getIpWhitelist() {
        return ipWhitelist;
    }

    public void setIpWhitelist(String ipWhitelist) {
        this.ipWhitelist = ipWhitelist;
    }
}
