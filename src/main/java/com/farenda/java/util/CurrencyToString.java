package com.farenda.java.util;

import java.util.Currency;
import java.util.Locale;

public class CurrencyToString {

    public static void main(String[] args) {
        // For Chinese Yuan:
        Locale locale = Locale.CHINA;
        Currency currency = Currency.getInstance(locale);
        System.out.println("Currency toString(): "
                + currency.toString());

        // For Euro currency:
        currency = Currency.getInstance("EUR");
        System.out.println("Currency toString(): "
                + currency.toString());
    }
}
