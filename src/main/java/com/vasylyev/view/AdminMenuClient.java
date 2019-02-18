package com.vasylyev.view;

import com.vasylyev.domain.Client;
import com.vasylyev.services.ClientService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.vasylyev.view.CommonMethods.InputString;

public class AdminMenuClient {

    //private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //private final ClientService clientService = new ClientServiceImpl();
    private final BufferedReader br;
    private final ClientService clientService;

    public AdminMenuClient(BufferedReader br, ClientService clientService) {
        this.br = br;
        this.clientService = clientService;
    }

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
        int age = Integer.parseInt(InputString("Input age: "));
        //int age = readInt();
        String phone = InputString("Input phone: ");
        String email = InputString("Input email: ");
        clientService.createClient(name, surname, age, phone, email);
    }

    private void modifyClient()  throws IOException {
        String name = InputString("Input name to find client: ");
        String newName = InputString("Input new name: ");
        clientService.modifyClient(name, newName);
    }

    private void getClientsList(){
        //clientService.getClientsList();
        int numberOfClients = 0;
        for (Client client : clientService.GetAllClients()) {
            System.out.println(client.toString());
            numberOfClients++;
        }
        System.out.println("Number of clients: " + numberOfClients);
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
/*
    private int readInt(){
        try{
            return Integer.parseInt(InputString("Input age: "));
        }catch(IOException | NumberFormatException nfe){
            System.err.println("Invalid Format!");
            return readInt();
        }
    }
    */
}
