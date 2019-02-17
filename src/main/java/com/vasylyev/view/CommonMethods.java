package com.vasylyev.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommonMethods {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String InputString(String messageToShow) throws IOException {
        System.out.println(messageToShow);
        return br.readLine();
    }

    public static int readInt(){
        try{
            return Integer.parseInt(InputString("Input age: "));
        }catch(IOException | NumberFormatException nfe){
            System.err.println("Invalid Format!");
            return readInt();
        }
    }


}
