package com.vasylyev.view;

import com.vasylyev.services.ClientService;
import com.vasylyev.services.impl.ClientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminMenu {

    //private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //private final ClientService clientService = new ClientServiceImpl();
    private final BufferedReader br;
    private final ClientService clientService;

    public AdminMenu(BufferedReader br, ClientService clientService) {
        this.br = br;
        this.clientService = clientService;
    }

    public void show() throws IOException {
        boolean isRunning = true;
        while(isRunning) {
            showMenu();
            switch (br.readLine()) {
                case "1":
                    AdminMenuClient adminMenuClient = new AdminMenuClient(br, clientService);
                    adminMenuClient.show();
                    break;
                case "2":
                    AdminMenuProduct adminMenuProduct = new AdminMenuProduct();
                    adminMenuProduct.show();
                    break;
                case "3":
                    AdminMenuOrder adminMenuOrder = new AdminMenuOrder();
                    adminMenuOrder.show();
                    break;
                case "9":
                    isRunning = false;
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
        }
    }

    private void showMenu() {
        System.out.println("1. Admin Client MENU");
        System.out.println("2. Admin Product MENU");
        System.out.println("3. Admin Order MENU");
        System.out.println("9. Return");
        System.out.println("0. Exit");
    }

}
