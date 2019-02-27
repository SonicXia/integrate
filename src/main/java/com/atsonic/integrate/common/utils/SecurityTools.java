package com.atsonic.integrate.common.utils;

import java.util.HashMap;
import java.util.Map;


public class SecurityTools {
    private static final String KEY = "3EI#mVbw^$2Xue8o";

    public static final String TOKEN_KEY = "token";

    public static String createToken(String username, String password) {
        long timestamp = System.currentTimeMillis();
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("timestamp", String.valueOf(timestamp));
        return HmacSHA256Utils.digest(KEY, params);
    }


}
