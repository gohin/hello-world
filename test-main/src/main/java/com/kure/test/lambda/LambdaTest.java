package com.kure.test.lambda;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

public class LambdaTest {
    public static void main(String[] args) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(LambdaTest.getUtilDate("20190630","yyyyMMdd"));

        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

//
//        List<String> strings = new ArrayList<>() {{
//            add(new String("aaa"));
//            add(new String("aaa"));
//            add(new String("aaa"));
//            add(new String("aaa"));
//            add(new String("aaa"));
//            add(new String("aaa"));
//        }
//        };
//        Consumer<String> consumer = System.out::println;
//
//        strings.forEach(consumer);
    }

    public static Date getUtilDate(String dateStr, String format) throws ParseException {
        if (dateStr != null && dateStr.length() != 0) {
            format = format == null ? "yyyy-MM-dd HH:mm:ss" : format;
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr);
        } else {
            return null;
        }
    }
}
