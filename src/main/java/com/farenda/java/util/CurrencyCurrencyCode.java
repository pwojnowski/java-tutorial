package com.farenda.java.util;

import java.util.Currency;
import java.util.Locale;

public class CurrencyCurrencyCode {

    public static void main(String[] args) {
        Locale locale = Locale.CHINA;
        Currency currency = Currency.getInstance(locale);
        //ISO 4217 code:
        System.out.println("Currency code: "
                + currency.getCurrencyCode());

        currency = Currency.getInstance(Locale.CANADA_FRENCH);
        System.out.println("Currency code: "
                + currency.getCurrencyCode());
    }
}
