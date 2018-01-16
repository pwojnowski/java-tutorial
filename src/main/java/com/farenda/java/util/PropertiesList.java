package com.farenda.java.util;

import java.util.Properties;

public class PropertiesList {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("connectionTime", "1200");
        props.setProperty("maxThreads", "50");

        props.list(System.out);
    }
}
