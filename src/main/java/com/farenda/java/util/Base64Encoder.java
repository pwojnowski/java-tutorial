package com.farenda.java.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Encoder {

    public static void main(String[] args) {
        String message = "Hello, world!\nAnkaŭ en alia lingvo!";
        System.out.println("Original: " + message);

        byte[] bytesToEncode = message.getBytes(StandardCharsets.UTF_8);

        byte[] encoded = encode(bytesToEncode);

        decode(encoded);
    }

    private static byte[] encode(byte[] bytesToEncode) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encoded = encoder.encode(bytesToEncode);
        System.out.println("Encoded: " + new String(encoded));
        return encoded;
    }

    private static void decode(byte[] encoded) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decoded = decoder.decode(encoded);
        System.out.println("Decoded: " + new String(decoded));
    }
}