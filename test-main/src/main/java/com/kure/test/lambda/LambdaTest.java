package com.kure.test.lambda;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class LambdaTest {
    public static void main(String[] args) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(LambdaTest.getUtilDate("20190630", "yyyyMMdd"));

        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

        Stream.iterate(0, i -> i + 1).limit(10).forEach(System.out::println);

        List<String> strings = new ArrayList<>() {
            {
                add(new String("aaa"));
                add(new String("aaa"));
                add(new String("aaa"));
                add(new String("aaa"));
                add(new String("aaa"));
                add(new String("aaa"));
            }
        };
        Consumer<String> consumer = System.out::println;
        Consumer<String> consumer1 = (t)-> {
            t = "333";
            System.out.println("t"+t);
        };
        strings.forEach(consumer);
        strings.forEach(consumer1);

    }

    public static Date getUtilDate(String dateStr, String format) throws ParseException {
        if (dateStr != null && dateStr.length() != 0) {
            format = format == null ? "yyyy-MM-dd HH:mm:ss" : format;
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr);
        } else {
            return null;
        }

//    public void getName(String name, HelloWorld helloWorld) {
//        System.out.println("hello:" + helloWorld.sayHi(name));
//    }

//    public static void main(String[] args) {
//        LambdaTest lambdaTest = new LambdaTest();
//        lambdaTest.getName("world", (a)-> {
//            try {
//                return a;
//            }catch (Exception e) {
//                throw e;
//            }
//        });
//
//        ParamCheck paramCheck = (a,b)-> {
//            System.out.println(a > b);
//        };
//        paramCheck.check(3,5);
//    }
    }
}
/**
 *
 */
@FunctionalInterface
interface ParamCheck{
    void check(int a, int b);
}

@FunctionalInterface
interface HelloWorld{
    String sayHi(String name);
}
