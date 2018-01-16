package com.farenda.java.util;

import java.util.Currency;
import java.util.Locale;

public class CurrencyGetDisplayNameWithLocale {

    public static void main(String[] args) {
        Currency currency = Currency.getInstance(Locale.CHINA);

        System.out.println("Currency: " + currency);
        System.out.println("French display name: "
                + currency.getDisplayName(Locale.FRANCE));

        System.out.println("Korean display name: "
                + currency.getDisplayName(Locale.KOREA));
    }
}
