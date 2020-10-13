package com.kure.test.reflect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@Aspect
public class AopArgsTest {

    @Pointcut("execution(* com.kure.test..*(..))")
    public void pointcut(){}


    @Around("pointcut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Annotation[][] annotationss = methodSignature.getMethod().getParameterAnnotations();

        Object[] objects = joinPoint.getArgs();
        Stream.iterate(0, i-> i + 1).limit(objects.length).forEach(integer -> {
            Stream.of(annotationss).forEach(annotations -> {
                Stream.of(annotations).forEach(annotation -> {
                    if (annotation instanceof NotNull) {
                        NotNull notNull = (NotNull) annotation;
                        objects[integer] = Optional.ofNullable(objects[integer]);
                    }
                });
            });
        });

//        for (int i = 0; i < objects.length; i++) {
//            for (Annotation[] annotations : annotationss) {
//                for (Annotation annotation : annotations) {
//                    System.out.println(annotation);
//                    NotNull notNull = (NotNull) annotation;
//                    System.out.println(notNull.value());
//                    int finalI = i;
//                    Stream.of(objects).forEach(arg->{
//                        if (StringUtils.isEmpty(arg)){
//                            objects[finalI] = notNull.value();
//                        }
//                    });
//                }
//            }
//        }

        joinPoint.proceed(objects);
        for (Object o : joinPoint.getArgs()) {
            System.out.println("args= " + o.toString());
        }
    }
}
