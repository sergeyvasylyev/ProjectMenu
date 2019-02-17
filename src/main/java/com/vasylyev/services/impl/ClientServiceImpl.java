package com.vasylyev.services.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.dao.impl.ClientDaoImpl;
import com.vasylyev.domain.Client;
import com.vasylyev.exceptions.BusinessException;
import com.vasylyev.services.ClientService;
import com.vasylyev.validators.ValidationService;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    //private final ClientDao clientDao = new ClientDaoImpl();
    //singletone
    //private ClientDao clientDao = ClientDaoImpl.getInstance();
    private ClientDao clientDao;
    private ValidationService validationService;

    //dependency injection
    public ClientServiceImpl(ClientDao clientDao, ValidationService validationService)   {
        this.clientDao = clientDao;
        this.validationService = validationService;
    }

    @Override
    public void createClient(String name, String surname, String phone) {
        //Client client = new Client(name, surname, phone);
        //clientDao.saveClient(client);
        this.createClient(name, surname, 0, phone, null);
    }

    @Override
    public void createClient(String name, String surname, int age, String phone, String email){
        try {
            validationService.validateAge(age);
            Client client = new Client(name, surname, age, phone, email);
            clientDao.saveClient(client);
        }
        catch(BusinessException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifyClient(String name, String newName) {
        Client tempClient = clientDao.findClient(name);
        System.out.println("Found client: " + tempClient);
        if (tempClient != null){
            clientDao.modifyClient(tempClient, newName);
        }
    }

    @Override
    public void deleteClient(String name) {
        Client tempClient = clientDao.findClient(name);
        System.out.println("Found client: " + tempClient);
        if (tempClient != null){
            clientDao.deleteClient(tempClient);
        }
    }

    @Override
    public void getClientsList(){
        List<Client> clientList = clientDao.getClientsList();
        for (int i = 0; i < clientList.size(); i++) {
            System.out.println(clientList.get(i).toString());
        }
        System.out.println("Number of clients: "+clientList.size());
    }

    public List<Client> GetAllClients(){
        return clientDao.getClientsList();
    };
}
