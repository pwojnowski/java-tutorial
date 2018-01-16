package com.farenda.java.util;

import java.util.Currency;
import java.util.Locale;

public class CurrencyGetNumericCode {

    public static void main(String[] args) {
        Locale locale = Locale.CHINA;
        Currency currency = Currency.getInstance(locale);
        System.out.printf("Numeric (ISO 4217) code of %s: %d%n",
                currency, currency.getNumericCode());

        locale = Locale.US;
        currency = Currency.getInstance(locale);
        System.out.printf("Numeric (ISO 4217) code of %s: %d%n",
                currency, currency.getNumericCode());
    }
}
