package com.farenda.java.util;

import java.util.Currency;

public class CurrencyGetInstanceString {

    public static void main(String[] args) {

        String name = "USD";
        Currency currency = Currency.getInstance(name);
        System.out.printf("Currency of %s: %s%n",
                name, currency.getDisplayName());

        name = "XXX";
        currency = Currency.getInstance(name);
        System.out.printf("Currency of %s: %s%n",
                name, currency);

    }
}
