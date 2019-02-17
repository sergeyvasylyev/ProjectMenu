package com.vasylyev.view;

import com.vasylyev.domain.Client;
import com.vasylyev.services.ClientService;
import com.vasylyev.services.OrderService;
import com.vasylyev.services.ProductService;
import com.vasylyev.services.impl.ClientServiceImpl;
import com.vasylyev.services.impl.OrderServiceImpl;
import com.vasylyev.services.impl.ProductServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.vasylyev.view.CommonMethods.InputString;

public class ClientMenu {

    //private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //private final ClientService clientService = new ClientServiceImpl();
    private final BufferedReader br;
    private final ClientService clientService;

    public ClientMenu(BufferedReader br, ClientService clientService) {
        this.br = br;
        this.clientService = clientService;
    }

    private final ProductService productService = new ProductServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();

    public void show() throws IOException {
        boolean isRunning = true;
        while (isRunning) {
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
                /*
                case "1":
                    findProduct();
                    break;
                case "2":
                    getProductsList();
                    break;
                case "3":
                    findOrder();
                    break;
                case "4":
                    getOrdersList();
                    break;
                    */
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

    private void findProduct()  throws IOException {
        String name = InputString("Input name to find product: ");
        productService.findProduct(name);
    }

    private void getProductsList(){
        productService.getProductsList();
    }

    private void findOrder() throws IOException {
        Long id = new Long(0);
        try{
            id = Long.valueOf(InputString("Input id to find order: "));
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
            return;
        }
        orderService.findOrder(id);
    }

    private void getOrdersList(){
        orderService.getOrdersList();
    }

    private void showMenu() {
        /*
        System.out.println("1. Find product");
        System.out.println("2. Show list of products");
        System.out.println("3. Find order");
        System.out.println("4. Show list of orders");
        System.out.println("9. Return");
        System.out.println("0. Exit");
        */
        System.out.println("1. Register");
        System.out.println("2. Modify");
        System.out.println("3. Remove");
        System.out.println("4. Get all clients");

        System.out.println("9. Return");
        System.out.println("0. Exit");
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


}
