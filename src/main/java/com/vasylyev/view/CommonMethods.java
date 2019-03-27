package com.vasylyev.view;

import com.vasylyev.domain.Order;
import com.vasylyev.domain.Product;
import com.vasylyev.services.OrderService;
import com.vasylyev.services.ProductService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CommonMethods {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String InputString(String messageToShow) throws IOException {
        System.out.println(messageToShow);
        return br.readLine();
    }

    public static int readInt(String messageToShow) {
        try {
            return Integer.parseInt(InputString(messageToShow));
        } catch (IOException | NumberFormatException nfe) {
            System.err.println("Invalid Format!");
            return readInt(messageToShow);
        }
    }

    public static long readLong(String messageToShow) {
        try {
            return Long.parseLong(InputString(messageToShow));
        } catch (IOException | NumberFormatException nfe) {
            System.err.println("Invalid Format!");
            return readLong(messageToShow);
        }
    }

    public static void getProductsList(ProductService productService) {
        int numberOfProducts = 0;
        for (Product product : productService.GetAllProducts()) {
            System.out.println(product.toStringLong());
            numberOfProducts++;
        }
        System.out.println("Number of products: " + numberOfProducts);
    }

    public static void getOrdersList(OrderService orderService) {
        int numberOfOrders = 0;
        for (Order order : orderService.GetAllOrders()) {
            System.out.println(order.toString());
            numberOfOrders++;
        }
        System.out.println("Number of orders: " + numberOfOrders);
    }

    public static void createOrder(OrderService orderService) throws IOException {
        long clientId = readLong("Input id to find client: ");
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
        orderService.createOrder(clientId, productList);
    }
}
