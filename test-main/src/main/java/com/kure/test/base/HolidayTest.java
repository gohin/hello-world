package com.kure.test.base;

public class HolidayTest {
    final static int holiday = 20190820;
    public static void main(String[] args) {
        System.out.println(getHoliday(20190811));
    }

    public static int getHoliday(int date){
        return date == holiday ? date : getHoliday(++date);
    }
}
