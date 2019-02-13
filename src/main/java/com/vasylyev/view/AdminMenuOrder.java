package com.vasylyev.view;

import com.vasylyev.services.OrderService;
import com.vasylyev.services.impl.OrderServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.vasylyev.view.CommonMethods.InputString;

public class AdminMenuOrder {

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final OrderService orderService = new OrderServiceImpl();

    public void show() throws IOException {
        boolean isRunning = true;
        while(isRunning) {
            showMenu();
            switch (br.readLine()) {
                case "1":
                    createOrder();
                    break;
                case "2":
                    deleteOrder();
                    break;
                case "3":
                    getOrdersList();
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

    private void createOrder() throws IOException {
        String clientName = InputString("Input client name to create order: ");
        ArrayList<String> productList = new ArrayList<String>();
        boolean isRunning = true;
        while (isRunning) {
            String productName = InputString("Input product name to add to the order (Press 0 to skip): ");
            if (productName.equals("0")) {
                isRunning = false;
                break;
            } else {
                productList.add(productName);
            }
        }
        orderService.createOrder(clientName, productList);
    }

    private void getOrdersList(){
        orderService.getOrdersList();
    }

    private void deleteOrder() throws IOException {
        Long id = new Long(0);
        try{
            id = Long.valueOf(InputString("Input id to find order: "));
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
            return;
        }
        orderService.deleteOrder(id);
    }


    private void showMenu() {
        System.out.println("1. Add order");
        System.out.println("2. Remove order");
        System.out.println("3. List all orders");
        System.out.println("9. Return");
        System.out.println("0. Exit");
    }
}
