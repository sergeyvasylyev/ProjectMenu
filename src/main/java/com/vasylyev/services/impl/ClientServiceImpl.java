package com.vasylyev.services.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.dao.impl.ClientDaoImpl;
import com.vasylyev.domain.Client;
import com.vasylyev.services.ClientService;

public class ClientServiceImpl implements ClientService {

    @Override
    public void createClient(String name, String surname, String phone) {
        Client client = new Client(name, surname, phone);
        ClientDao clientDao = new ClientDaoImpl();
        clientDao.saveClient(client);
    }

    @Override
    public void deleteClient() {

    }
}
