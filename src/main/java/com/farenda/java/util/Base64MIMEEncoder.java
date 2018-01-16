package com.farenda.java.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64MIMEEncoder {
    private static String MESSAGE
            = "Java SE 8 represents the single largest evolution"
            + " of the Java language in its history. A relatively"
            + " small number of features - lambda expressions, method"
            + " references, and functional interfaces - combine to offer"
            + " a programming model that fuses the object-oriented and"
            + " functional styles. Under the leadership of Brian Goetz,"
            + " this fusion has been accomplished in a way that encourages"
            + " best practices - immutability, statelessness,"
            + " compositionality - while preserving \"the feel of Java\""
            + " - readability, simplicity, universality.\n";

    public static void main(String[] args) {
        System.out.println("Original:\n" + MESSAGE);

        byte[] bytesToEncode = MESSAGE.getBytes(StandardCharsets.UTF_8);

        byte[] encoded = encode(bytesToEncode);

        decode(encoded);
    }

    private static byte[] encode(byte[] bytesToEncode) {
        Base64.Encoder encoder = Base64.getMimeEncoder();
        byte[] encoded = encoder.encode(bytesToEncode);
        System.out.println("Encoded:\n" + new String(encoded));
        return encoded;
    }

    private static void decode(byte[] encoded) {
        Base64.Decoder decoder = Base64.getMimeDecoder();
        byte[] decoded = decoder.decode(encoded);
        System.out.println("Decoded:\n"
                + new String(decoded, StandardCharsets.UTF_8));
    }
}