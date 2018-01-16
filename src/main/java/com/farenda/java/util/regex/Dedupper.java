package com.farenda.java.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dedupper {

    public static void main(String[] args) {
        String input = "The the string String string stringing.";

        String regex = "\\b(\\w+)(\\s+\\1\\b)+";
        Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        Matcher m = p.matcher(input);
        while (m.find()) {
            String group0 = m.group();
            String group1 = m.group(1);
            System.out.printf("'%s' and '%s'%n", group0, group1);
            input = input.replaceAll(group0, group1);
        }

        System.out.println(input);
    }
}
