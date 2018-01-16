package com.farenda.java.util;

import java.util.Currency;
import java.util.Locale;

public class CurrencyGetSymbolLocale {

    public static void main(String[] args) {
        Locale locale = new Locale("pl", "PL");
        Currency currency = Currency.getInstance(locale);

        // Symbol of Polish PLN in PL locale:
        System.out.printf("Symbol of %s: %s%n",
                currency, currency.getSymbol(locale));

        // Symbol of Polish PLN in US locale:
        System.out.printf("Symbol of %s: %s%n",
                currency, currency.getSymbol(Locale.US));

        currency = Currency.getInstance(Locale.US);
        // Symbol of US Dollar in US locale:
        System.out.printf("Symbol of %s: %s%n",
                currency, currency.getSymbol(Locale.US));
    }
}
