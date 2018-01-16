package com.farenda.java.util;

import java.util.Currency;
import java.util.Locale;

public class CurrencyGetDisplayName {

    public static void main(String[] args) {
        Currency currency = Currency.getInstance(Locale.CHINA);

        System.out.println("Currency: " + currency);
        System.out.println("Display name: "
                + currency.getDisplayName());

        currency = Currency.getInstance(Locale.KOREA);
        System.out.println("Currency: " + currency);
        System.out.println("Display name: "
                + currency.getDisplayName());
    }
}
