package com.kure.test.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * yyyy -- 小写表示year
 * YYYY -- 大写表示week year
 */
public class SimpleDateFormatTest {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // week year
        SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY");

        System.out.println(sdf1.format(sdf.parse("2019-12-01")));

        System.out.println(sdf1.format(sdf.parse("2019-12-30")));

        System.out.println(sdf1.format(sdf.parse("2020-01-01")));

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");

        System.out.println(sdf2.format(sdf.parse("2019-12-01")));

        System.out.println(sdf2.format(sdf.parse("2019-12-30")));

        System.out.println(sdf2.format(sdf.parse("2020-01-01")));
    }
}
