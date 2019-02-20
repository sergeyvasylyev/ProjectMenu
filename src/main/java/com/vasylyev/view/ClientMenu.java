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
import java.util.ArrayList;

import static com.vasylyev.view.CommonMethods.InputString;
import static com.vasylyev.view.CommonMethods.readLong;

public class ClientMenu {
    private final BufferedReader br;
    private final ClientService clientService;
    private final ProductService productService;
    private final OrderService orderService;

    public ClientMenu(BufferedReader br, ClientService clientService, ProductService productService, OrderService orderService) {
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
                    getProductsList();
                    break;
                case "4":
                    createOrder();
                    break;
                case "5":
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

    private void createClient() throws IOException {
        String name = InputString("Input name: ");
        String surname = InputString("Input surname: ");
        String phone = InputString("Input phone: ");
        clientService.createClient(name, surname, phone);
    }

    private void modifyClient() throws IOException {
        long clientId = readLong("Input id to find client: ");
        String newName = InputString("Input new name: ");
        clientService.modifyClient(clientId, newName);
    }

    private void getProductsList() {
        CommonMethods.getProductsList(productService);
    }

    private void createOrder() throws IOException {
        CommonMethods.createOrder(orderService);
    }

    private void getOrdersList() {
        CommonMethods.getOrdersList(orderService);
    }

    private void showMenu() {
        System.out.println("1. Register");
        System.out.println("2. Modify");
        System.out.println("3. List products");
        System.out.println("4. Add order");
        System.out.println("5. List orders");

        System.out.println("R. Return");
        System.out.println("E. Exit");
    }


}
