package com.farenda.java.util;

import java.util.Formattable;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import static java.util.Arrays.asList;
import static java.util.FormattableFlags.*;

public class FormattableExample {

    public static class Book implements Formattable {

        private String symbol;
        private String bookName;
        private String polishName;

        public Book(String symbol, String bookName, String polishName) {
            this.symbol = symbol;
            this.bookName = bookName;
            this.polishName = polishName;
        }

        @Override
        public void formatTo(Formatter fmt, int flags, int width, int precision) {
            StringBuilder sb = new StringBuilder();

            String name = computeName(fmt, flags, precision);
            applyPrecision(precision, sb, name);
            applyFilling(flags, width, sb);

            fmt.format(sb.toString());
        }

        private void applyFilling(int flags, int minWidth, StringBuilder sb) {
            int currentLength = sb.length();
            if (currentLength < minWidth) {
                // prepend or append spaces depending on justification
                boolean leftJustified = isEnabled(flags, LEFT_JUSTIFY);
                for (int i = 0, toAdd = minWidth - currentLength; i < toAdd; i++) {
                    if (leftJustified) {
                        sb.append(' ');
                    } else {
                        sb.insert(0, ' ');
                    }
                }
            }
        }

        private void applyPrecision(int precision, StringBuilder sb, String out) {
            if (unspecified(precision) || fitsInPrecision(precision, out)) {
                sb.append(out);
            } else {
                sb.append(out.substring(0, precision-1)).append('*');
            }
        }

        private boolean fitsInPrecision(int precision, String out) {
            return out.length() < precision;
        }

        private boolean unspecified(int precision) {
            return precision == -1;
        }

        private String computeName(Formatter fmt, int flags, int precision) {
            return shouldUseSymbol(flags, precision) ? symbol : getLocalizedName(fmt);
        }

        private String getLocalizedName(Formatter fmt) {
            return fmt.locale().equals(Locale.forLanguageTag("pl_PL"))
                    ? polishName : bookName;
        }

        private boolean shouldUseSymbol(int flags, int precision) {
            return isEnabled(flags, ALTERNATE) || (precision != -1 && precision < 10);
        }

        private boolean isEnabled(int flags, int leftJustify) {
            return (flags & leftJustify) == leftJustify;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s", symbol, bookName);
        }
    }

    public static void main(String[] args) {
        List<Book> books = asList(
                new Book("GOF",
                        "Design Patterns: Elements of Reusable Object-Oriented Software",
                        "Wzorce projektowe. Elementy oprogramowania obiektowego wielokrotnego użytku"),
                new Book("Wizard Book",
                        "Structure and Interpretation of Computer Programs",
                        "Struktura i interpretacja programów komputerowych"));

        for (Book book : books) {
            printBook(book);
        }
    }

    private static void printBook(Book gof) {
        System.out.println("Format: formatted output");
        System.out.printf("\"%%s\"(toString()): '%s'%n", gof.toString());
        System.out.printf("\"%%s\": '%s'%n", gof);
        System.out.printf("\"%%#s\"(alternate): '%#s'%n", gof); // alternate format
        System.out.printf("\"%%-10.5s\"(left,width,precision): '%-10.5s'%n", gof);
        System.out.printf("\"%%.13s\": '%.13s'%n", gof);
        System.out.printf(Locale.forLanguageTag("pl_PL"), "\"%%25s\"(pl_PL): '%25s'%n", gof);
        System.out.println();
    }
}
