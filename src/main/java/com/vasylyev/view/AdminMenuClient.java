package com.vasylyev.view;

import com.vasylyev.services.ClientService;
import com.vasylyev.services.impl.ClientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.vasylyev.view.CommonMethods.InputString;

public class AdminMenuClient {

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final ClientService clientService = new ClientServiceImpl();

    public void show() throws IOException {
        boolean isRunning = true;
        while(isRunning) {
            showMenu();
            switch (br.readLine()) {
                case "1":
                    createClient();
                    break;
                case "2":
                    modifyClient();
                    break;
                case "3":
                    deleteClient();
                    break;
                case "4":
                    getClientsList();
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

    private void createClient() throws IOException {
        String name = InputString("Input name: ");
        String surname = InputString("Input surname: ");
        String phone = InputString("Input phone: ");
        clientService.createClient(name, surname, phone);
    }

    private void modifyClient()  throws IOException {
        String name = InputString("Input name to find client: ");
        String newName = InputString("Input new name: ");
        clientService.modifyClient(name, newName);
    }

    private void getClientsList(){
        clientService.getClientsList();
    }

    private void deleteClient() throws IOException {
        String name = InputString("Input name to find client: ");
        clientService.deleteClient(name);
    }

    private void showMenu() {
        System.out.println("1. Add client");
        System.out.println("2. Modify client");
        System.out.println("3. Remove client");
        System.out.println("4. List all clients");
        System.out.println("9. Return");
        System.out.println("0. Exit");
    }
}
