package com.farenda.java.security;

import com.google.common.hash.Hashing;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SecurityPlayground {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String data = "Hello World";
        System.out.printf("MD5 of '%s': %s%n", data, md5(data));
        System.out.printf("MD5 in Guava of '%s': %s%n", data, guavaMd5(data));

        sha256();
    }

    private static String guavaMd5(String data) {
        return Hashing.md5().hashString(data, UTF_8).toString();
    }

    private static String md5(String data) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(data.getBytes(UTF_8));
        return String.format("%032x%n", new BigInteger(1, digest));
    }

    private static void sha256() throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("SHA-256");
        byte[] digest = md5.digest("aoeu".getBytes(Charset.defaultCharset()));
        System.out.printf("%064x%n", new BigInteger(1, digest));
    }
}
