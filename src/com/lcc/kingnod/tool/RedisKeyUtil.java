package com.lcc.kingnod.tool;

/**
 * Created by asus on 2017/3/21.
 */
public class RedisKeyUtil {

    public static final String CACHE_ROOT_NAME = "kingnod";
    public static final String CACHE_KEY_SEPARATOR = ":";
    public static final String CACHE_CPY="cpy";
    public static final String USER_ID="userId";

    /**
     * 拼接cpy key
     */
    public static String getKey(String... keys) {
        StringBuilder sb = new StringBuilder("");
        sb.append(CACHE_ROOT_NAME).append(CACHE_KEY_SEPARATOR);
        int i = 0;
        for (String key : keys) {
            sb.append(key);
            if(i < keys.length - 1 ){
                sb.append(CACHE_KEY_SEPARATOR);
            }
            i++;
        }
        return  sb.toString();
    }
}
