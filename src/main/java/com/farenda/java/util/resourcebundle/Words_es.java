package com.farenda.java.util.resourcebundle;

import java.util.*;

public class Words_es extends ResourceBundle {

    private static final Map<String, String> WORDS = new HashMap<>();
    static {
        // Load from some data source
        //WORDS.put("book", "el libro");
        WORDS.put("date-format", "yyyy.MM.dd");
    }

    @Override
    protected Object handleGetObject(String key) {
        // Return null for unknown key to allow
        // check other Resource Bundles
        return WORDS.get(key);
    }

    @Override
    protected Set<String> handleKeySet() {
        // Faster implementation than default one
        return WORDS.keySet();
    }

    @Override
    public Enumeration<String> getKeys() {
        return Collections.enumeration(WORDS.keySet());
    }
}
