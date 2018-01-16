package com.farenda.java.lang;

public class ReverseString {

    public static void main(String[] args) {
        String saluton = "Saluton, la mondo!";
        System.out.println("Original: " + saluton);

        withStringBuilder(saluton);

        charArrayReverse(saluton);
    }

    private static void withStringBuilder(String saluton) {
        String reversed = new StringBuilder(saluton).reverse().toString();
        System.out.println("Reversed 1: " + reversed);
    }

    private static void charArrayReverse(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0, n = s.length()-1; i < n; ++i, --n) {
            char c = chars[i];
            chars[i] = chars[n];
            chars[n] = c;
        }
        String r = new String(chars);
        System.out.println("Reversed 2: " + r);
    }
}
