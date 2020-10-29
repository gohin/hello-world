package com.kure.test.reflect;

import com.kure.test.reflect.domain.ReflectDo;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.stream.Stream;

/**
 * BeanUtil
 * BeanInfo
 * Introspector 是操作 javaBean 的 API，用来访问某个属性的 getter/setter 方法
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception {

        Stream.of(BeanUtils.getPropertyDescriptors(ReflectDo.class)).forEach(propertyDescriptor -> {
            System.out.println("Spring="+propertyDescriptor.getName());
            if (!"class".equals(propertyDescriptor.getName())){
                System.out.println(propertyDescriptor.getReadMethod());
            }
        });


        BeanInfo beanInfo = Introspector.getBeanInfo(ReflectDo.class);
        Stream.of(beanInfo.getMethodDescriptors()).forEach(methodDescriptor -> {
            System.out.println("methodDescriptor=" + methodDescriptor.getMethod().getName());
        });
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        Stream.of(propertyDescriptors).forEach(propertyDescriptor -> {
            System.out.println("propertyDescriptor=" + propertyDescriptor.getReadMethod());
            System.out.println("propertyDescriptor=" + propertyDescriptor.getWriteMethod());
            System.out.println("propertyDescriptor=" + propertyDescriptor.getName());
        });

        Stream.of(beanInfo.getBeanDescriptor()).forEach(beanDescriptor -> {
            System.out.println("beanDescriptor=" + beanDescriptor.getDisplayName());
        });

        Field[] fields = ReflectDo.class.getDeclaredFields();
        Stream.of(fields).forEach(field -> {
            MyAnno anno = field.getDeclaredAnnotation(MyAnno.class);
            if(field.isAnnotationPresent(MyAnno.class)){
                System.out.println(anno.value());
            }
        });
    }
}
