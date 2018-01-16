package com.farenda.java.util.resourcebundle;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.ResourceBundle.getBundle;

public class ResourceBundleExample {

    private static final String BUNDLE_NAME
            = "com.farenda.java.util.resourcebundle.Words";

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        displayWord("book", Locale.getDefault());
//        displayWord("book", Locale.JAPAN);
//        displayWord("book", new Locale("pl", "PL"));
//        displayWord("book", new Locale("eo"));
        displayWord("book", new Locale("es", "ES"));

        displayDate(Locale.CANADA);
        displayDate(new Locale("es", "ES"));
    }

    private static void displayDate(Locale locale) {
        ResourceBundle bundle = getBundle(BUNDLE_NAME, locale);
        String localeDate = formatDate(bundle.getString("date-format"));
        System.out.printf("19th May 2015 in locale '%s': %s%n",
                locale, localeDate);
    }

    private static String formatDate(String dateFormat) {
        Date date = Date.from(Instant.parse("2015-05-19T00:00:00.00Z"));
        return new SimpleDateFormat(dateFormat).format(date);
    }

    private static void displayWord(String word, Locale locale) {
        ResourceBundle words = getBundle(BUNDLE_NAME, locale);
        System.out.printf("'%s' in locale '%s': %s%n",
                word, locale, words.getString(word));
    }
}
