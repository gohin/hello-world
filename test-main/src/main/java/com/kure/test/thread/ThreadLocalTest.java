package com.kure.test.thread;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过反射获取threadlocalmap
 */
public class ThreadLocalTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Thread t1  = Thread.currentThread();
        ThreadLocal<Map<String, String>> threadLocalMap = ThreadLocal.withInitial(()-> new HashMap<>());
        Map<String, String> map = new HashMap<>(8);
        map.put("name","kure");
        threadLocalMap.set(map);
        threadLocalMap.get();
        Field field1 =  t1.getClass().getDeclaredField("threadLocals");
        field1.setAccessible(true);
        Object obj = field1.get(t1);
        Field tableField = obj.getClass().getDeclaredField("table");
        tableField.setAccessible(true);
        Object[] table = (Object[])tableField.get(obj);
        for(int i=0;i<table.length;i++){
            Object entry = table[i];
            if(entry != null){
                WeakReference<ThreadLocal> threadLocalRef = (WeakReference<ThreadLocal>)entry;
                ThreadLocal threadLocal = threadLocalRef.get();
                if(threadLocal != null){
                    System.out.println(threadLocal.get());
                }
            }
        }
    }
}
