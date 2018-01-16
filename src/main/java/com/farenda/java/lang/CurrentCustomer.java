package com.farenda.java.lang;

import java.util.function.Supplier;

public class CurrentCustomer {

    private static final Supplier<String> STATE_CHECKER = () -> {
        throw new IllegalStateException("Customer must be set!");
    };

    private static final ThreadLocal<String> customer
            = ThreadLocal.withInitial(STATE_CHECKER);

    public static String get() {
        return customer.get();
    }

    public static void set(String id) {
        customer.set(id);
    }
}
