package com.vasylyev.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class CommonMethods {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String InputString(String messageToShow) throws IOException {
        System.out.println(messageToShow);
        return br.readLine();
    }

    public static int readInt(String messageToShow) {
        try {
            return Integer.parseInt(InputString(messageToShow));
        } catch (IOException | NumberFormatException nfe) {
            System.err.println("Invalid Format!");
            return readInt(messageToShow);
        }
    }

    public static long readLong(String messageToShow) {
        try {
            return Long.parseLong(InputString(messageToShow));
        } catch (IOException | NumberFormatException nfe) {
            System.err.println("Invalid Format!");
            return readLong(messageToShow);
        }
    }
}
