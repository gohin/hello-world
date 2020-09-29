package com.kure.test.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * SDP下的工具类
 *
 * @author txh
 * @version 1.0.0
 * @ClassName com.erayt.xfunds.sdp.common/SdpUtils.java
 * @createTime 2019年10月10日 上午11:37:53
 */
@Component("Sdp_bankUtils")
public final class SdpUtils {
    private static final Logger logger = LoggerFactory.getLogger(SdpUtils.class);

    /**
     * List中String数据转换成String字符数据
     *
     * @param strList
     * @param seqStr
     * @return
     * @date 2019年10月10日上午11:38:33
     * @version 1.0.0
     */
    public static String convertListToStr(List<String> strList, String seqStr) {
        if (strList == null || strList.isEmpty()) {
            return null;
        }
        if (strList.size() == 1) {
            return strList.get(0);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(strList.get(0));
        for (int i = 1, len = strList.size(); i < len; i++) {
            sb.append(seqStr).append(strList.get(i));
        }
        return sb.toString();
    }

    public static boolean isEmpty(Collection list) {
        return null == list || list.isEmpty();
    }


    public static boolean isEmpty(BigDecimal value) {
        return null == value || value.compareTo(BigDecimal.ZERO) == 0;
    }

    public static boolean isEmpty(Map map) {
        return null == map || map.isEmpty();
    }

    public static <T> boolean isEmpty(T[] array) {
        return null == array || array.length == 0;
    }

    public static boolean isEmpty(Object object) {
        return null == object || StringUtils.isEmpty(object.toString());
    }


    public static List<Field> getAllFieldOf(Class clazz) {
        List<Field> ret;
        if (clazz.getSuperclass() != null) {
            ret = getAllFieldOf(clazz.getSuperclass());
        } else {
            ret = new ArrayList<>();
        }
        List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        Iterator<Field> fieldIters = fields.iterator();
        //ret.stream().filter(f->)
        List<Field> filteredFieldList = ret.stream().filter(f -> {
            while (fieldIters.hasNext()) {
                if (f.getName().equals(fieldIters.next().getName())) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
        if (!filteredFieldList.isEmpty()) {
            fields.addAll(filteredFieldList);
        }
        return fields;
    }

    /**
     * 将bean1内与bean2中字段名和字段类型相同的值赋值到bean2中
     *
     * @param bean1
     * @param bean2
     * @param <T1>
     * @param <T2>
     */
    public static <T1, T2> void setSameFieldValue(T1 bean1, T2 bean2) {
        Class clazz1 = bean1.getClass();
        Class clazz2 = bean2.getClass();
        List<Method> getNameMethodList = Arrays.stream(clazz1.getMethods()).filter(beanMethod -> beanMethod.getName().startsWith("get"))
                .collect(Collectors.toList());
        List<Method> setNameMethodList = Arrays.stream(clazz2.getMethods()).filter(beanMethod -> beanMethod.getName().startsWith("set"))
                .collect(Collectors.toList());
        getNameMethodList.forEach(getNameMethod -> {
            setNameMethodList.stream()
                    .filter(setNameMethod -> getNameMethod.getName().substring(2).equals(setNameMethod.getName().substring(2)))
                    .findFirst()
                    .ifPresent(existsSetMethod -> {
                        try {
                            Object value = getNameMethod.invoke(bean1);
                            Class[] paramClassList = existsSetMethod.getParameterTypes();
                            if (paramClassList.length == 1 && value != null && paramClassList[0].isAssignableFrom(value.getClass())) {
                                existsSetMethod.invoke(bean2, value);
                            }
                        } catch (Exception e) {
                            logger.error("获取值失败，失败原因:{}", e.getMessage(), e);
                        }
                    });
        });
    }


    /**
     * 将字符串中的占位符根据参数的toString方法进行替换
     *
     * @param
     * @return
     * @author LiBinbing
     * @date 2020/4/15 9:43
     * @version 1.0
     **/
    public static String replacePlaceHolder(String str, Object... objs) {
        if (isEmpty(str) || isEmpty(objs)) {
            return str;
        }
        String leftQuote = "{";
        String rightQuote = "}";
        int idx = -1;
        int beginIdx = 0;
        StringBuilder sb = new StringBuilder();
        int objIdx = 0;
        final int objectMaxIdx = objs.length - 1;
        while ((idx = str.indexOf(leftQuote, beginIdx)) != -1) {
            sb.append(str.substring(beginIdx, idx));
            int rightQuoteIdx = str.indexOf(rightQuote, idx);
            if (rightQuoteIdx == -1) {
                beginIdx = str.length();
                sb.append(str.substring(idx));
                break;
            } else {
                beginIdx = rightQuoteIdx + 1;
            }

            if (objIdx <= objectMaxIdx) {
                sb.append(objs[objIdx++]);
            } else {
                sb.append(' ');
            }
        }

        if (beginIdx == 0) {
            return str;
        }
        if (beginIdx < str.length()) {
            sb.append(str.substring(beginIdx));
        }
        return sb.toString();
    }


    public static <T extends Closeable> void close(T stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                logger.error("关闭流出错:" + e.getMessage(), e);
            }
        }
    }

    /**
     * 对象转字符串
     *
     * @param objTmp
     * @throws
     * @throws
     * @version 1.0
     * @created 2019/8/28 13:58
     * @mod 修改描述:
     * @modAuthor 修改人:
     */
    public static String convertObjToStr(Object objTmp) {
        if (objTmp == null) {
            return null;
        }
        Object obj = objTmp;
        if (obj instanceof List) {
            List<Object> objList = (List<Object>) obj;
            if (!objList.isEmpty()) {
                obj = objList.get(0);
            }
            if (obj == null) {
                return null;
            }
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        return obj.toString();
    }

    public static <T, T1> boolean isAnyMatch(T[] arr, Class<T1> t1) {
        return Arrays.stream(arr)
                .anyMatch(t -> t1.equals(t.getClass()));
    }

    public static <T, T1> void ifAnyMatchThrow(T[] arr, Class<T1> t1, Function<T1, Throwable> function) throws Throwable {
        Optional<T1> optional = (Optional<T1>) Arrays.stream(arr)
                .filter(t1::equals)
                .findAny();
        if (optional.isPresent()) {
            throw function.apply(optional.get());
        }
    }

    /**
     * <p>Title </p>
     * <p>Description </p>
     * 反射调用出错时，获取真实的错误信息
     *
     * @param
     * @return
     * @author MyBatisGroup
     * @date 2020/4/30 14:29
     * @version 1.0
     **/
    public static Throwable unwrapThrowable(Throwable wrapped) {
        Throwable unwrapped = wrapped;
        while (true) {
            if (unwrapped instanceof InvocationTargetException) {
                unwrapped = ((InvocationTargetException) unwrapped).getTargetException();
            } else if (unwrapped instanceof UndeclaredThrowableException) {
                unwrapped = ((UndeclaredThrowableException) unwrapped).getUndeclaredThrowable();
            } else {
                return unwrapped;
            }
        }
    }



    public static <T> T checkNull(T obj, String msg) {
        if (obj == null) {
        }
        return obj;
    }

    /**
     * <p>Title </p>
     * <p>Description </p>
     * 根据父类上的TypeParam找到实际子类指定的类型
     *
     * @param obj        子类的实例
     * @param iSuperClass 指定父类
     * @param iTypeName   父类上的typeParam的命名
     * @return Class  子类在父类上指定的typeParam的实际实现类
     * @author netty group
     * @author LiBinbing
     * @date 2020/8/4 15:04
     * @version 1.0
     **/
    public static Class<?> findRealTypeOfTypeParam(Object obj, Class<?> iSuperClass, String iTypeName) {
        final Class<?> thisClass = obj.getClass();
        Class<?> currentClass = thisClass;
        Class<?> superClass = iSuperClass;
        String typeName = iTypeName;
        for (; ; ) {
            if (currentClass.getSuperclass() == superClass) {
                int typeParamIndex = -1;
                TypeVariable<?>[] typeParams = currentClass.getSuperclass().getTypeParameters();
                for (int i = 0; i < typeParams.length; i++) {
                    if (typeName.equals(typeParams[i].getName())) {
                        typeParamIndex = i;
                        break;
                    }
                }
                if (typeParamIndex < 0) {
                }
                Type genericSuperType = currentClass.getGenericSuperclass();
                if (!(genericSuperType instanceof ParameterizedType)) {
                    return Object.class;
                }
                Type[] actualTypeParams = ((ParameterizedType) genericSuperType).getActualTypeArguments();

                Type actualTypeParam = actualTypeParams[typeParamIndex];
                if (actualTypeParam instanceof ParameterizedType) {
                    actualTypeParam = ((ParameterizedType) actualTypeParam).getRawType();
                }
                if (actualTypeParam instanceof Class) {
                    return (Class<?>) actualTypeParam;
                }
                if (actualTypeParam instanceof GenericArrayType) {
                    Type componentType = ((GenericArrayType) actualTypeParam).getGenericComponentType();
                    if (componentType instanceof ParameterizedType) {
                        componentType = ((ParameterizedType) componentType).getRawType();
                    }
                    if (componentType instanceof Class) {
                        return Array.newInstance((Class<?>) componentType, 0).getClass();
                    }
                }
                if (actualTypeParam instanceof TypeVariable) {
                    TypeVariable<?> v = (TypeVariable<?>) actualTypeParam;
                    currentClass = thisClass;
                    if (!(v.getGenericDeclaration() instanceof Class)) {
                        return Object.class;
                    }

                    superClass = (Class<?>) v.getGenericDeclaration();
                    typeName = v.getName();
                    if (superClass.isAssignableFrom(thisClass)) {
                        continue;
                    } else {
                        return Object.class;
                    }
                }
            }

            currentClass = currentClass.getSuperclass();
            if (currentClass == null) {
            }
        }
    }

}
