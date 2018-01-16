package com.farenda.java.util;

import java.util.Comparator;

/**
 * Compares Users using only their names.
 */
public class NameComparator implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        return u1.getName().compareTo(u2.getName());
    }
}
