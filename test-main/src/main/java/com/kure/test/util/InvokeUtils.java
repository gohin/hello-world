package com.kure.test.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName InvokeUtils
 * @Description 反射工具类
 * @Author TANGYE
 * @Date 2018/6/11 15:56
 * @Version 1.0
 **/
public final class InvokeUtils {
    private static final Logger logger = LoggerFactory.getLogger(InvokeUtils.class);

    private static ConcurrentHashMap<String, HashMap<String, Field>> field = new ConcurrentHashMap<String, HashMap<String, Field>>();
    private static ConcurrentHashMap<String, HashMap<String, Method>> method = new ConcurrentHashMap<String, HashMap<String, Method>>();
    private static ConcurrentHashMap<String, HashMap<String, Annotation>> annotation = new ConcurrentHashMap<String, HashMap<String, Annotation>>();
    private static ConcurrentHashMap<String, Class<? extends Object>> objectMap = new ConcurrentHashMap<String, Class<? extends Object>>();

    private InvokeUtils(){}
    /**
     * <p>Title: 获取指定类的实例</p>
     * <p>Description:</p>
     * @author TANGYE
     * @since 2017-12-19下午4:45:33
     * @param cl
     * @return T
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public static <T> T getInstance(Class<T> cl){
        try {
            return (T) Class.forName(cl.getName()).newInstance();
        } catch (InstantiationException e) {
            logger.error(e.getMessage(),e);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(),e);
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * <p>Title: 获取指定类的实例</p>
     * <p>Description:</p>
     * @author TANGYE
     * @since 2017-12-19下午4:45:33
     * @param className
     * @return T
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static <T> T getInstance(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (T) Class.forName(className).newInstance();
    }

    /**
     * <p>Title:</p>
     * <p>Description:</p>
     * @author TANGYE
     * @since 2018-1-5下午2:10:57
     * @param className
     * @return Class
     * @throws ClassNotFoundException
     */
    public static Class<?> getClassByName(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }

    /**
     * <p>Title: 执行某个实例的某个方法</p>
     * @author TANGYE
     * @since 2017-12-19下午4:45:38
     * @param obj 实例
     * @param methodName 方法名
     * @param args 方法参数
     * @return T
     */
    public static <T> T invokeMethod(Object obj, String methodName, Object[] args) {

        Method method = getMethod(obj.getClass(),methodName);
        if (null == method){
            logger.error("Method：{} not found in {}" ,methodName, obj.getClass().getSimpleName());
        }
        Object a = null;
        try {
            a = method.invoke(obj, args);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
            Throwable throwable = e.getTargetException();
            logger.error(throwable.getMessage(),throwable);
        }
        return (T) a;
    }

    /**
     * <p>Title: 执行某个实例的某个方法</p>
     * @author TANGYE
     * @since 2017-12-19下午4:45:38
     * @param cl 实例.class
     * @param methodName 方法名
     * @param args 方法参数
     * @return T
     */
    public static <T> T invokeMethod(Class<?> cl, String methodName, Object[] args) {
        return invokeMethod(getInstance(cl), methodName, args);
    }

        /**
         * <p>Title: 执行某个实例的某个方法</p>
         * <p>Description:</p>
         * @author TANGYE
         * @since 2017-12-19下午4:45:38
         * @param className 完整类名
         * @param methodName 方法名
         * @param args 方法参数
         * @return T
         */
        public static <T> T invokeMethod(String className , String methodName, Object[] args)
            throws ClassNotFoundException {
            return invokeMethod(getClassByName(className), methodName, args);
        }

    /**
      * @Description 执行指定类的有参构造方法
      * @author TANGYE
      * @since 2018/6/16 16:23
      * @param className 完整类名
      * @param args 参数
      * @return Object
      * @Version 1.0
      **/
    public static Object doInstanceWithArgs(String className, Object[] args){
        Class<?> clazz;
        try {
            clazz = getClassByName(className);
            Class<?>[] cl = new Class[args.length];
            for(int i = 0; i<args.length;i++){
                cl[i] = args[i].getClass();
            }
            Constructor<?> c = clazz.getConstructor(cl);
            return c.newInstance(args);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }

    /**
     * 根据Class获取指定Field
     * @param clazz     Class
     * @param fieldName 字段名field.getName
     * @return java.lang.reflect.Field
     * @author TANGYE
     * @since 2018/9/4 16:28
     **/
    public Field getField(Class<?> clazz, String fieldName) {
        initClassInfo(clazz);
        HashMap<String, Field> map = field.get("_" + clazz.getName());
        return map.get(fieldName);
    }

    /**
     * 获取Field的注解
     * @param clazz               Class
     * @param fieldName           字段名field.getName
     * @param annotationClassName 注解Class.getName
     * @return java.lang.annotation.Annotation
     * @author TANGYE
     * @since 2018/9/4 16:39
     **/
    public Annotation getAnnotation(Class<?> clazz, String fieldName, String annotationClassName) {
        initClassInfo(clazz);
        HashMap<String, Annotation> map = annotation.get("_" + clazz.getName());
        return map.get(fieldName + annotationClassName);
    }

    /**
     * 根据Class获取指定的Method
     * @param clazz
     * @param methodName
     * @return java.lang.reflect.Method
     * @author TANGYE
     * @since 2018/9/4 16:36
     **/
    public static Method getMethod(Class<?> clazz, String methodName) {
        initClassInfo(clazz);
        HashMap<String, Method> map = method.get("_" + clazz.getName());
        return map.get(methodName);
    }

    /**
     * <p>Description 根据全类限定名返回指定的类对象</p>
     * @param classFullName
     * @return Class
     * @author LiBinbing
     * @date 2018/10/25 8:58
     **/
    public static Class<?> getClass(String classFullName) throws ClassNotFoundException {
        Class<?> value = objectMap.get("_" + classFullName);
        if (null == value) {
            addClassInfoToCache(value = InvokeUtils.getClassByName(classFullName));
        }
        return value;
    }
    private static void recursiveClass(List<Field> fields, List<Method> methods, Class tempClass) {
        if (tempClass.getSuperclass() != null) {
            recursiveClass(fields, methods, tempClass.getSuperclass());
        }
        fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
        methods.addAll(Arrays.asList(tempClass.getDeclaredMethods()));
    }
    /**
     * 将类信息放入缓存
     * @param clazz
     * @return void
     * @author TANGYE
     * @since 2019/1/7 15:35
     **/
    private static void addClassInfoToCache(Class<?> clazz) {
        Object object = InvokeUtils.getInstance(clazz);
        List<Field> fields = new ArrayList<Field>();
        List<Method> methods = new ArrayList<Method>();
        Class tempClass = object.getClass();
        recursiveClass(fields, methods, tempClass);
        Field proxyH = null;
        Field type = null;

        HashMap<String, Field> fieldMap = new HashMap<String, Field>();
        HashMap<String, Method> methodMap = new HashMap<String, Method>();
        HashMap<String, Annotation> annotationMap = new HashMap<String, Annotation>();
        for (Field field : fields) {
            field.setAccessible(true);
            fieldMap.put(field.getName(), field);
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Proxy) {
                    Object obj;
                    Class<?> realClass = null;
                    try {
                        if (proxyH == null || type == null) {
                            proxyH = Proxy.class.getDeclaredField("h");
                            proxyH.setAccessible(true);
                            obj = proxyH.get(annotation);
                            type = obj.getClass().getDeclaredField("type");
                            type.setAccessible(true);
                            realClass = (Class<?>) type.get(obj);
                        } else {
                            obj = proxyH.get(annotation);
                            realClass = (Class<?>) type.get(obj);
                        }
                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage(), e);
                    } catch (NoSuchFieldException e) {
                        logger.error(e.getMessage(), e);
                    }
                    if (null != realClass) {
                        annotationMap.put(field.getName() + realClass.getName(), annotation);
                    }
                } else {
                    annotationMap.put(field.getName() + annotation.getClass().getName(), annotation);
                }
            }
        }
        for (Method method : methods) {
            method.setAccessible(true);
            methodMap.put(method.getName(), method);
        }
//        field.put(ConstantsBaseFather.MODULE_INVOKE_FIELD + "_" + object.getClass().getName(), fieldMap);
//        method.put(ConstantsBaseFather.MODULE_INVOKE_METHOD + "_" + object.getClass().getName(), methodMap);
//        annotation.put(ConstantsBaseFather.MODULE_INVOKE_ANNOTATION + "_" + object.getClass().getName(), annotationMap);
//        objectMap.put(ConstantsBaseFather.MODULE_CLASS_INFO + "_" + object.getClass().getName(), object.getClass());
    }

    /**
     * 如果缓存中没有Class信息，则新增到缓存中
     * @param clazz Class
     * @return void
     * @author TANGYE
     * @since 2018/9/4 16:27
     **/
    private static void initClassInfo(Class<?> clazz) {
        Class<?> value = objectMap.get("_" + clazz.getName());
        if (null == value) {
            addClassInfoToCache(clazz);
        }
    }

}