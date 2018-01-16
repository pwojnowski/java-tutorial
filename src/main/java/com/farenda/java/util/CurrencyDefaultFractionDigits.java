package com.farenda.java.util;

import java.util.Currency;
import java.util.Locale;

public class CurrencyDefaultFractionDigits {

    public static void main(String[] args) {
        Locale locale = Locale.FRANCE;
        Currency currency = Currency.getInstance(locale);
        System.out.printf("Fraction digits of %s: %d%n",
                currency, currency.getDefaultFractionDigits());

        locale = Locale.JAPAN;
        currency = Currency.getInstance(locale);
        System.out.printf("Fraction digits of %s: %d%n",
                currency, currency.getDefaultFractionDigits());

        currency = Currency.getInstance("XBC");
        System.out.printf("Fraction digits of %s: %d%n",
                currency, currency.getDefaultFractionDigits());
    }
}
