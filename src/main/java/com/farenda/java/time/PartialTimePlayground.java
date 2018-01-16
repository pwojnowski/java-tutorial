package com.farenda.java.time;

import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;

public class PartialTimePlayground {

    public static void main(String[] args) {
        year();
        yearMonth();
        monthDay();
    }

    private static void monthDay() {
        MonthDay christmas = MonthDay.of(Month.DECEMBER, 25);
        System.out.println("Christmas: " + christmas);
    }

    private static void yearMonth() {
        YearMonth plannedBirth = YearMonth.of(2017, Month.SEPTEMBER);
        System.out.println(plannedBirth);
    }

    private static void year() {
        Year past = Year.of(1910);
        System.out.printf("Was %s a leap year? %b%n", past, past.isLeap());
    }
}
