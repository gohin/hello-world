package com.kure.test.util;

import org.mvel2.MVEL;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class SdpMvenUtil {
    /** 字符集编码格式 */
    public static final String SYS_CODE_UTF_8 = "UTF-8";
    private SdpMvenUtil(){
    }
    /**
     * 用于保存表达式，和已经编译过的对象，key为表达式，值为编译后的对象
     */
    private static Map<String, Serializable> compiles = new ConcurrentHashMap<String, Serializable>();

    /**
     * @Title: executeExpression
     * @Description: 执行表达式，没有返回
     * @param expressino
     * @param lc
     * @param
     * @throws
     * @author fuzhenhui
     * @version 1.0
     * @created 2017-5-8 下午1:47:38
     */
    public static void executeExpression(String expressino, Map lc) {
        Serializable compiled = queryCompiled(expressino, compiles);
        if (compiled == null) {
            return;
        }
        MVEL.executeExpression(compiled, lc);
    }

    /**
     * @Title: executeExpreAndReturnObj
     * @Description: 执行表达式，有返回对象
     * @param expressino
     * @param lc
     * @param
     * @return
     * @throws
     * @author fuzhenhui
     * @version 1.0
     * @created 2017-5-8 下午1:48:25
     */
    public static Object executeExpreAndReturnObj(String expressino, Map lc) {
        Serializable compiled = queryCompiled(expressino, compiles);
        if (compiled == null) {
            return null;
        }
        return MVEL.executeExpression(compiled, lc);
    }

    /**
     * @Title: encodeKey
     * @Description: 将字符串用BASE64Encoder转码，使得结果不会出现特殊字符
     * @param key
     * @return
     * @throws
     * @author fuzhenhui
     * @version 1.0
     * @created 2017-5-8 下午2:22:45
     */
    private static String encodeKey(String key) {
        if (StringUtils.isNotBlank(key)) {
            // return new BASE64Encoder().encode(key.getBytes(SYS_CODE_UTF_8));
            return key.trim(); // 暂不用BASE64Encoder方法
        }
        return "";
    }

    /**
     * @Title: queryCompiled
     * @Description: 依据key值获取map中的编译后的文件，没有则重新编译
     * @param expressino
     * @param maps
     * @return
     * @throws
     * @author fuzhenhui
     * @version 1.0
     * @created 2017-5-8 下午2:46:44
     */
    private static Serializable queryCompiled(String expressino, Map<String, Serializable> maps) {
        String key = encodeKey(expressino);
        if (StringUtils.isBlank(key)) {
            return null;
        }
        Serializable compiled;
        compiled = maps.get(key);
        if (compiled == null) {
            compiled = MVEL.compileExpression(expressino);
            maps.put(key, compiled);
        }
        return compiled;
    }
}
