package com.vasylyev.services.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.dao.impl.ClientDaoImpl;
import com.vasylyev.domain.Client;
import com.vasylyev.exceptions.BusinessException;
import com.vasylyev.services.ClientService;
import com.vasylyev.validators.ValidationService;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao;
    private ValidationService validationService;

    public ClientServiceImpl(ClientDao clientDao, ValidationService validationService) {
        this.clientDao = clientDao;
        this.validationService = validationService;
    }

    @Override
    public void createClient(String name, String surname, String phone) {
        this.createClient(name, surname, 0, null, phone);
    }

    @Override
    public void createClient(String name, String surname, int age, String email, String phone) {
        try {
            validationService.validateAge(age);
            validationService.validatePhone(phone);
            if (email != null) {
                validationService.validateEmail(email);
            }

            Client tempClient = clientDao.findClient(phone);
            if (tempClient != null) {
                System.out.println("Client exist! " + tempClient.toString());
                return;
            }
            ;
            Client client = new Client(name, surname, age, email, phone);
            clientDao.saveClient(client);
        } catch (BusinessException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifyClient(Long id, String newName) {
        Client tempClient = clientDao.findClient(id);
        System.out.println("Found client: " + tempClient);
        if (tempClient != null) {
            clientDao.modifyClient(tempClient, newName);
        }
    }

    @Override
    public void deleteClient(Long id) {
        Client tempClient = clientDao.findClient(id);
        System.out.println("Found client: " + tempClient);
        if (tempClient != null) {
            clientDao.deleteClient(tempClient);
        }
    }

    public List<Client> GetAllClients() {
        return clientDao.getClientsList();
    }

    ;
}
