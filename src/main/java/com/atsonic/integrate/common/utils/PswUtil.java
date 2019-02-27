package com.atsonic.integrate.common.utils;

import com.atsonic.integrate.common.config.MyConfig;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Sonic
 */
@Component
public class PswUtil {

    @Autowired
    private MyConfig myConfig;

    public static PswUtil pswUtil;

    public PswUtil() {
    }

    @PostConstruct
    public void init() {
        pswUtil = this;
        pswUtil.myConfig = this.myConfig;
    }

    /**
     * 初始化密码
     *
     * @param salt
     * @return
     */
    public String generateDefaultPsw(String salt) {
        return new Md5Hash(myConfig.getInitialPsw(), salt).toString();
    }


}
