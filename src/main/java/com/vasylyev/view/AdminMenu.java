package com.vasylyev.view;

import com.vasylyev.domain.Client;
import com.vasylyev.domain.Order;
import com.vasylyev.domain.Product;
import com.vasylyev.services.ClientService;
import com.vasylyev.services.OrderService;
import com.vasylyev.services.ProductService;
import com.vasylyev.services.impl.ClientServiceImpl;
import com.vasylyev.services.impl.OrderServiceImpl;
import com.vasylyev.services.impl.ProductServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;

import static com.vasylyev.view.CommonMethods.*;

public class AdminMenu {

    private final BufferedReader br;
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderService orderService;

    public AdminMenu(BufferedReader br, ClientService clientService, ProductService productService, OrderService orderService) {
        this.br = br;
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
    }

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
                case "5":
                    createProduct();
                    break;
                case "6":
                    modifyProduct();
                    break;
                case "7":
                    deleteProduct();
                    break;
                case "8":
                    getProductsList();
                    break;
                case "9":
                    createOrder();
                    break;
                case "10":
                    deleteOrder();
                    break;
                case "11":
                    getOrdersList();
                    break;
                case "R":
                    isRunning = false;
                    break;
                case "E":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input!");
                    break;
            }
        }
    }

    //Clients
    private void createClient() throws IOException {
        String name = InputString("Input name: ");
        String surname = InputString("Input surname: ");
        int age = readInt("Input age: ");
        String phone = InputString("Input phone: ");
        String email = InputString("Input email: ");
        clientService.createClient(name, surname, age, phone, email);
    }

    private void modifyClient() throws IOException {
        long clientId = readLong("Input id to find client: ");
        String newName = InputString("Input new name: ");
        clientService.modifyClient(clientId, newName);
    }

    private void getClientsList() {
        int numberOfClients = 0;
        for (Client client : clientService.getAllClients()) {
            System.out.println(client.toString());
            numberOfClients++;
        }
        System.out.println("Number of clients: " + numberOfClients);
    }

    private void deleteClient() throws IOException {
        long clientId = readLong("Input id to find client: ");
        clientService.deleteClient(clientId);
    }

    //Products
    private void createProduct() throws IOException {
        String name = InputString("Input name: ");
        BigDecimal price;
        try {
            price = new BigDecimal(InputString("Input price: "));
        } catch (IOException | NumberFormatException nfe) {
            System.err.println("Invalid Format!");
            return;
        }
        productService.createProduct(name, price);
    }

    private void modifyProduct() throws IOException {
        String name = InputString("Input name to find product: ");
        String newName = InputString("Input new name: ");
        productService.modifyProduct(name, newName);
    }

    private void getProductsList() {
        CommonMethods.getProductsList(productService);
    }

    private void deleteProduct() throws IOException {
        String name = InputString("Input name to find product: ");
        productService.deleteProduct(name);
    }

    //Orders
    private void createOrder() throws IOException {
        CommonMethods.createOrder(orderService);
    }

    private void getOrdersList() {
        CommonMethods.getOrdersList(orderService);
    }

    private void deleteOrder() {
        long orderId = readLong("Input id to find order: ");
        orderService.deleteOrder(orderId);
    }

    private void showMenu() {
        System.out.println("1. Add client");
        System.out.println("2. Modify client");
        System.out.println("3. Remove client");
        System.out.println("4. List all clients");

        System.out.println("5. Add product");
        System.out.println("6. Modify product");
        System.out.println("7. Remove product");
        System.out.println("8. List all products");

        System.out.println("9. Add order");
        System.out.println("10. Remove order");
        System.out.println("11. List all orders");

        System.out.println("R. Return");
        System.out.println("E. Exit");
    }

}
