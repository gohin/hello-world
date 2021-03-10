package com.kure.test.spring.springmvc.anno;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface MyAutowired {
    String value() default "";
}
