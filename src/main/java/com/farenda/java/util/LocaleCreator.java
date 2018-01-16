package com.farenda.java.util;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import static java.text.DateFormat.DEFAULT;
import static java.text.DateFormat.getDateInstance;

public class LocaleCreator {

    public static void main(String[] args) {
        System.out.println("Locales from constants:");
        printResult(Locale.CANADA);
        printResult(Locale.CHINA);

        System.out.println("Locales using constructors:");
        // language, country, variant
        printResult(new Locale("pl"));
        printResult(new Locale("en", "US"));
        printResult(new Locale("gr", "GR", "polyton"));

        System.out.println("Locales using Builder:");
        Locale locale = new Locale.Builder()
                .setLanguage("en").setRegion("GB")
                .build();
        printResult(locale);
        locale = new Locale.Builder()
                .setLanguage("zh").setRegion("CN")
                .build();
        printResult(locale);
        locale = new Locale.Builder()
                .setLanguage("ru").setScript("Cyrl")
                .build();
        printResult(locale);

        System.out.println("Locales using forLanguageTag factory method:");
        printResult(Locale.forLanguageTag("pl-PL"));
        printResult(Locale.forLanguageTag("jp-JP-u-ca-japanese"));
    }

    private static void printResult(Locale locale) {
        DateFormat dateFormat = getDateInstance(DEFAULT, locale);
        String formattedDate = dateFormat.format(new Date());
        System.out.printf("Date in %s locale: %s%n", locale, formattedDate);
    }
}
