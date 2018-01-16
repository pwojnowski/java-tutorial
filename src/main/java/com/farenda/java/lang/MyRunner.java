package com.farenda.java.lang;

public class MyRunner {

    public static void main(String[] args) {
        SimpleProcessor processor = () -> 42;
        System.out.println("Processing: " + processor.process());
    }
}
