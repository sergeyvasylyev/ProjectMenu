package com.vasylyev.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

@Component("menu")
public class MainMenu {

    private final BufferedReader br;
    private final AdminMenu adminMenu;
    private final ClientMenu clientMenu;

    public MainMenu(BufferedReader br, AdminMenu adminMenu, ClientMenu clientMenu) {
        this.br = br;
        this.adminMenu = adminMenu;
        this.clientMenu = clientMenu;
    }

    public void showMenu() throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1. Admin");
            System.out.println("2. Client");
            System.out.println("E. Exit");

            switch (br.readLine()) {
                case "1":
                    adminMenu.show();
                    break;
                case "2":
                    clientMenu.show();
                    break;
                case "E":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
        }
    }
}
