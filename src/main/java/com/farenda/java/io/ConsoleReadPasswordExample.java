package com.farenda.java.io;

import java.io.Console;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ConsoleReadPasswordExample {

    public static void main(String[] args) {
        Console console = System.console();

        Objects.requireNonNull(console, "Run only as console application!");

        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedPassword =
                encoder.encodeToString("javarules!".getBytes(UTF_8));

        String username = console.readLine("Username: ");
        char[] password = console.readPassword("%s password: ", username);

        if (password != null) {
            String providedPassword = encoder.encodeToString(
                    new String(password).getBytes(UTF_8));

            // for security reasons delete password from memory:
            Arrays.fill(password, 'x');

            if (encryptedPassword.equals(providedPassword)) {
                console.printf("Access Granted!%n");
            } else {
                console.printf("Wrong username or password!%n");
            }
        }
    }
}
