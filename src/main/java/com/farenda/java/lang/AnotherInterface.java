package com.farenda.java.lang;

import java.util.Date;

public interface AnotherInterface {

    default String formatDate(Date date) {
        return "2010";
    }
}
