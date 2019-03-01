package com.vasylyev;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.dao.OrderDao;
import com.vasylyev.dao.ProductDao;
import com.vasylyev.dao.impl.*;
import com.vasylyev.services.ClientService;
import com.vasylyev.services.OrderService;
import com.vasylyev.services.ProductService;
import com.vasylyev.services.impl.ClientServiceImpl;
import com.vasylyev.services.impl.OrderServiceImpl;
import com.vasylyev.services.impl.ProductServiceImpl;
import com.vasylyev.validators.ValidationService;
import com.vasylyev.validators.impl.ValidationServiceImpl;
import com.vasylyev.view.AdminMenu;
import com.vasylyev.view.ClientMenu;
import com.vasylyev.view.MainMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) throws IOException {

        ClientDao clientDao = new ClientDBDao();
        ProductDao productDao = new ProductDBDao();
        OrderDao orderDao = new OrderDBDao();

        ValidationService validationService = new ValidationServiceImpl();

        ClientService clientService = new ClientServiceImpl(clientDao, validationService);
        ProductService productService = new ProductServiceImpl(productDao, validationService);
        OrderService orderService = new OrderServiceImpl(clientDao, productDao, orderDao, validationService);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        AdminMenu adminMenu = new AdminMenu(br, clientService, productService, orderService);
        ClientMenu clientMenu = new ClientMenu(br, clientService, productService, orderService);


        MainMenu menu = new MainMenu(br, adminMenu, clientMenu);
        menu.showMenu();
    }
}
