package com.kure.test.util;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 线程级缓存
 * 如果value为空,将缓存{@code ThreadLocalCacheUtils.NULL_VALUE},防止缓存穿透问题.
 */
public final class ThreadLocalCacheutil {
    /**
     * 表示不存在该值,可防止缓存穿透问题.
     */
    public final static Object NULL_VALUE = new Object();

    public final static String LOGIN_USER ="LOGIN_USER";

    private static final ThreadLocal<Map<String, Object>> LOCAL_CACHE = ThreadLocal.withInitial(() -> new HashMap<>(16));

    public static Object get(String key){
        if(StringUtils.isEmpty(key)){
            return null;
        }
        return LOCAL_CACHE.get().get(key);
    }

    public static void cache(String key,Object value){
        if(StringUtils.isEmpty(key)){
            return;
        }
        if(null == value){
            LOCAL_CACHE.get().put(key,NULL_VALUE);
            return;
        }
        LOCAL_CACHE.get().put(key,value);
    }

    public static void clearAll(){
        LOCAL_CACHE.get().clear();
    }

    private ThreadLocalCacheutil(){}

}
