package com.vasylyev;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.dao.impl.ClientDaoImpl;
import com.vasylyev.services.ClientService;
import com.vasylyev.services.impl.ClientServiceImpl;
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
        //MainMenu menu = new MainMenu();

        ClientDao clientDao = ClientDaoImpl.getInstance();

        ValidationService validationService = new ValidationServiceImpl();

        ClientService clientService = new ClientServiceImpl(clientDao, validationService);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        AdminMenu adminMenu = new AdminMenu(br, clientService);
        ClientMenu clientMenu = new ClientMenu(br, clientService);


        MainMenu menu = new MainMenu(br, adminMenu, clientMenu);
        menu.showMenu();
    }
}
