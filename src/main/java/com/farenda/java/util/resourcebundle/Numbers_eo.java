package com.farenda.java.util.resourcebundle;

import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

// Make it thread-safe if used concurrently!
public class Numbers_eo extends ListResourceBundle {

    private static final Object[][] TRANSLATIONS = {
        {"sum-of-numbers", "%s plus %s estas %s"},
        {"one", "unu"},
        {"two", "du"},
        {"three", "tri"},
        {"arr", new String[] {"1","2","3"}}
    };

    @Override
    protected Object[][] getContents() {
        return TRANSLATIONS;
    }

    private static final String BUNDLE_NAME
            = "com.farenda.java.util.resourcebundle.Numbers";

    public static void main(String[] args) {
        ResourceBundle bundle = getBundle(BUNDLE_NAME, new Locale("eo"));

        translate(bundle, "one");
        translate(bundle, "two");
        translate(bundle, "three");

        System.out.printf(
                bundle.getString("sum-of-numbers"),
                bundle.getString("one"),
                bundle.getString("two"),
                bundle.getString("three"));

        for (String s : bundle.getStringArray("arr")) {
            System.out.println(s);
        }
    }

    private static void translate(ResourceBundle bundle, String key) {
        System.out.printf("'%s' in Esperanto: %s%n",
                key, bundle.getString(key));
    }
}
