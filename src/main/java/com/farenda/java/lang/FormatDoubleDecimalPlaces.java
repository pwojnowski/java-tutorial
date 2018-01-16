package com.farenda.java.lang;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatDoubleDecimalPlaces {

    public static void main(String[] args) {
        stringFormat();
        stringFormatLeadingZero();

        decimalFormat();
        decimalFormatTrailingZero();
        decimalFormatWithZeros();

        formatCustomLocale();
    }

    private static void stringFormat() {
        double number = 1234.5678;
        String s = String.format("%.2f", number);
        System.out.println(s);
    }

    private static void stringFormatLeadingZero() {
        double number = 0.5678;
        String s = String.format("%.2f", number);
        System.out.println(s);
    }

    private static void decimalFormat() {
        DecimalFormat df = new DecimalFormat("#.##");
        String s = df.format(1234.5678);
        System.out.println(s);
    }

    private static void decimalFormatTrailingZero() {
        DecimalFormat df = new DecimalFormat("#.##");
        String s = df.format(123.0);
        System.out.println(s);
    }

    private static void decimalFormatWithZeros() {
        DecimalFormat df = new DecimalFormat("#.00");
        String s = df.format(123.0);
        System.out.println(s);
    }

    private static void formatCustomLocale() {
        Locale locale = Locale.ENGLISH;
        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        System.out.println(nf.format(.99));
        System.out.println(nf.format(123.567));
        System.out.println(nf.format(123.0));
    }
}
