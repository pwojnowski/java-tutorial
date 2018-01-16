package com.farenda.java.util;

import java.util.Currency;
import java.util.Set;

public class CurrencyAvailableCurrencies {

    public static void main(String[] args) {
        // Since Java 7
        Set<Currency> available = Currency.getAvailableCurrencies();
        System.out.printf("There are %d available currencies:%n",
                available.size());

        for (Currency currency : available) {
            // print the currency:
            System.out.printf("Currency: %s, name: %s%n",
                    currency, currency.getDisplayName());
        }
    }
}
