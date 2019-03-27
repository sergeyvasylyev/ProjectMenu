package com.vasylyev.services.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.domain.Client;
import com.vasylyev.exceptions.BusinessException;
import com.vasylyev.services.ClientService;
import com.vasylyev.validators.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;
    private ValidationService validationService;

    public ClientServiceImpl(ClientDao clientDao, ValidationService validationService) {
        this.clientDao = clientDao;
        this.validationService = validationService;
    }

    public ClientServiceImpl() {
    }

    @Override
    public void createClient(String name, String surname, String phone) {
        this.createClient(name, surname, 0, phone, null);
    }

    @Override
    public void createClient(String name, String surname, int age, String phone, String email) {
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

            Client client = new Client.Builder(name, phone)
                    .surname(surname)
                    .age(age)
                    .email(email)
                    .build();

            clientDao.saveClient(client);
        } catch (BusinessException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifyClient(Long id, String newName) {
        Client tempClient = findClient(id);
        if (tempClient != null) {
            clientDao.modifyClient(tempClient, newName);
        }
    }

    @Override
    public Client findClient(Long id) {
        Client tempClient = clientDao.findClient(id);
        showClient(id, tempClient);
        return tempClient;
    }

    @Override
    public void deleteClient(Long id) {
        Client tempClient = findClient(id);
        if (tempClient != null) {
            clientDao.deleteClient(tempClient);
        }
    }

    public List<Client> getAllClients() {
        return clientDao.getClientsList();
    }

    static void showClient(Long id, Client client){
        String clientResult;
        if (client != null) {
            clientResult = "Found client: " + client;
        }
        else {
            clientResult = "Client not found: " + id;
        }
        System.out.println(clientResult);
    }
}
