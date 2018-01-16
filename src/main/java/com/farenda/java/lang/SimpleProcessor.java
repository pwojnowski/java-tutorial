package com.farenda.java.lang;

import java.util.Date;

@FunctionalInterface
public interface SimpleProcessor {

    int process();

    //int postProcess();

    static Date now() {
        return new Date();
    }

    default String formatDate(Date date) {
        return date.toString();
    }

    default int sum(int a, int b) {
        return a + b;
    }
}
