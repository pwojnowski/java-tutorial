package com.farenda.java.util.regex;

import java.util.regex.Pattern;

public class IpAddressValidator {

    private static final String zeroTo255
            = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";

    private static final String IP_REGEXP
            = zeroTo255 + "\\." + zeroTo255 + "\\."
            + zeroTo255 + "\\." + zeroTo255;

    private static final Pattern IP_PATTERN
            = Pattern.compile(IP_REGEXP);

    private boolean isValid(String address) {
        return IP_PATTERN.matcher(address).matches();
    }

    public static void main(String[] args) {
        IpAddressValidator validator = new IpAddressValidator();

        String[] ips = {
                "1.2.3.4",
                "000.12.23.034",
                "121.234.9.1",
                "23.45.56.12",
                "255.255.255.255",
                "255.1.0.256",
                "00.11.22.33.44",
                "123.45",
                "a123.45.44.43",
                "Im.not.IP.address"
        };

        for (String ip : ips) {
            System.out.printf("%20s: %b%n", ip, validator.isValid(ip));
        }
    }
}
