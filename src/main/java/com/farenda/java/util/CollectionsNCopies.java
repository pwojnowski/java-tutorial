package com.farenda.java.util;

import java.util.Collections;

public class CollectionsNCopies {

    static class MyObject {
        int x = 3;
    }

    public static void main(String[] args) {
        MyObject o = new MyObject();
        System.out.println("object: " + o + " with x = " + o.x);

        nTimes(3, o);

        o.x = 42;
        nTimes(3, o);
    }

    private static void nTimes(int i, MyObject proto) {
        System.out.println("Result:");
        for (MyObject mo : Collections.nCopies(i, proto)) {
            System.out.println(mo + " with x = " + mo.x);
        }
    }
}
