package com.farenda.java.util;

import com.farenda.java.time.ZoneIdExample;

import java.time.zone.ZoneRules;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarGetInstance {

    public static void main(String[] args) {
//        calendarWithDefaults();
//
//        calendarWithTimeZoneAndLocale();
//
//        createCalendarUsingJava8Builder();
//
//        availableCalendars();

        Calendar cal = Calendar.getInstance();
        cal.set(2017, 10, 23, 18, 30, 43);
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        printDetails(cal);
    }

    private static void calendarWithDefaults() {
        System.out.println("Calendar with default TimeZone and Locale");
        printDetails(Calendar.getInstance());
    }

    private static void calendarWithTimeZoneAndLocale() {
        System.out.println("Calendar with EST TimeZone and Italian Locale");
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("EST"), Locale.ITALIAN);
        printDetails(calendar);
    }

    private static void createCalendarUsingJava8Builder() {
        System.out.println("Create using Java 8 Calendar.Builder");
        printDetails(new Calendar.Builder().setCalendarType("gregory").build());
    }

    private static void availableCalendars() {
        System.out.println("Available calendars: " + Calendar.getAvailableCalendarTypes());
        for (String type : Calendar.getAvailableCalendarTypes()) {
            Calendar calendar = new Calendar.Builder()
                    .setCalendarType(type)
                    .setInstant(new Date())
                    .build();
            printDetails(calendar);
        }
    }

    private static void printDetails(Calendar calendar) {
        System.out.println("Calendar type: " + calendar.getCalendarType());
        System.out.println("First day of the week: " + calendar.getFirstDayOfWeek());
        System.out.println("Time since the Epoch: " + calendar.getTime());
        System.out.println("Current TimeZone: " + calendar.getTimeZone().getDisplayName());
        try {
            System.out.println("Weeks in year: " + calendar.getWeeksInWeekYear());
        } catch (UnsupportedOperationException e) {
            System.out.println("Weeks in year: UnsupportedOperationException");
        }
        try {
            System.out.println("The week year: " + calendar.getWeekYear());
        } catch (UnsupportedOperationException e) {
            System.out.println("The week year: UnsupportedOperationException");
        }
        System.out.println("As instant: " + calendar.toInstant());
        System.out.println();
    }
}
