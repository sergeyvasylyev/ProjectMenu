package com.vasylyev.services.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.dao.impl.ClientDaoImpl;
import com.vasylyev.domain.Client;
import com.vasylyev.services.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao = new ClientDaoImpl();

    @Override
    public void createClient(String name, String surname, String phone) {
        Client client = new Client(name, surname, phone);
        clientDao.saveClient(client);
    }

    @Override
    public void modifyClient(String name, String newName) {
        Client tempClient = clientDao.findClient(name);
        System.out.println("Found client: " + tempClient);
        if (tempClient != null){
            clientDao.modifyClient(tempClient, newName);
        }
    }

    public void deleteClient(String name) {
        Client tempClient = clientDao.findClient(name);
        System.out.println("Found client: " + tempClient);
        if (tempClient != null){
            clientDao.deleteClient(tempClient);
        }
    }

    public void getClientsList(){
        List<Client> clientList = clientDao.getClientsList();
        for (int i = 0; i < clientList.size(); i++) {
            System.out.println(clientList.get(i).toString());
        }
        System.out.println("Number of clients: "+clientList.size());
    }
}
