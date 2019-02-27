package com.atsonic.integrate.common.utils;

import java.util.UUID;

/**
 * Created by Administrator on 2018/5/10.
 */
public class IDUtils {

    public static String createUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
