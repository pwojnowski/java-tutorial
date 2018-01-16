package com.farenda.java.util;

import java.util.Currency;
import java.util.Locale;

public class CurrencyGetInstanceLocale {

    public static void main(String[] args) {
        Locale locale = Locale.CHINA;
        Currency currency = Currency.getInstance(locale);
        System.out.printf("Currency of %s: %s%n",
                locale, currency.getDisplayName());

        locale = new Locale("pl", "PL");
        currency = Currency.getInstance(locale);
        System.out.printf("Currency of %s: %s%n",
                locale, currency.getDisplayName());
    }
}
