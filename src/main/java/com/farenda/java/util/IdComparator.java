package com.farenda.java.util;

import java.util.Comparator;

/**
 * Compares Users using only their Ids.
 */
public class IdComparator implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        return Long.compare(u1.getId(), u2.getId());
    }
}
