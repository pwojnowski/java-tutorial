package com.farenda.java.util;

import java.util.Currency;
import java.util.Locale;

public class CurrencyGetSymbol {

    public static void main(String[] args) {
        System.out.println("Current default DISPLAY locale: "
                + Locale.getDefault(Locale.Category.DISPLAY));

        displaySymbol(new Locale("pl", "PL"));
        displaySymbol(Locale.US);

        Locale.setDefault(Locale.Category.DISPLAY, Locale.US);

        System.out.println("Current default DISPLAY locale: "
                + Locale.getDefault(Locale.Category.DISPLAY));

        displaySymbol(new Locale("pl", "PL"));
        displaySymbol(Locale.US);
    }

    private static void displaySymbol(Locale locale) {
        Currency currency = Currency.getInstance(locale);
        System.out.printf("Symbol of %s: %s%n",
                currency, currency.getSymbol());
    }
}
