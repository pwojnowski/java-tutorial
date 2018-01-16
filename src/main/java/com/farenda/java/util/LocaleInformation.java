package com.farenda.java.util;

import java.util.Locale;

public class LocaleInformation {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
//        Locale locale = Locale.getDefault();
//        System.out.println("Default locale: " + locale);

        availableLocales();

//        displayName();
//        displayCountry();
//        displayLanguage();
    }

    private static void displayLanguage() {
        Locale locale = Locale.JAPAN;
        System.out.printf("language: %s, name: %s%n",
                locale.getLanguage(), locale.getDisplayLanguage());
    }

    private static void displayCountry() {
        Locale locale = Locale.JAPAN;
        System.out.printf("country code: %s, name: %s%n",
                locale.getCountry(), locale.getDisplayCountry());
    }

    private static void displayName() {
        Locale locale = Locale.JAPAN;
        System.out.println("Name: " + locale.getDisplayName());
    }

    private static void availableLocales() {
        System.out.println("All installed locales:");
        for (Locale locale : Locale.getAvailableLocales()) {
            System.out.println("Name: " + locale.getDisplayName());
        }
    }
}
