package com.vasylyev.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainMenu {

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final AdminMenu adminMenu = new AdminMenu();
    private final ClientMenu clientMenu = new ClientMenu();

    public void showMenu() throws IOException {
        boolean isRunning = true;
        while (isRunning) {

            System.out.println("1. Admin");
            System.out.println("2. Client");
            System.out.println("0. Exit");

            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(in);
            //можно попробовать через Scanner считать данные
            switch (br.readLine()){
                case "1":
                    //System.out.println("SHOW ADMIN MENU");
                    adminMenu.show();
                    break;
                case "2":
                    System.out.println("SHOW CLIENT MENU");
                    break;
                case "0":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong input!");
                    break;
            }

        }
    }
}