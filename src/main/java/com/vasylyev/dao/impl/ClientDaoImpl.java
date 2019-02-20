package com.vasylyev.dao.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.domain.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.get;

public class ClientDaoImpl implements ClientDao {

    private static ClientDao clientDao;

    private Map<Long, Client> map = new HashMap<>();
    private static long generator = 0;

    private ClientDaoImpl() {
    }

    @Override
    public void saveClient(Client client) {
        client.setId(generator++);
        System.out.println("Save client: " + client.getName());
        map.put(client.getId(), client);
    }

    @Override
    public Client findClient(String phoneNumber) {
        for (long i = 0; i < map.size(); i++) {
            Client foundClient = map.get(i);
            if (phoneNumber.equals(foundClient.getPhone())) {
                return foundClient;
            }
            ;
        }
        return null;
    }

    @Override
    public Client findClient(Long id) {
        Client foundClient = map.get(id);
        return foundClient;
    }

    @Override
    public void modifyClient(Client client, String newName) {
        client.setName(newName);
        System.out.println("Save client: " + client.getName());
    }

    @Override
    public List<Client> getClientsList() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void deleteClient(Client client) {
        for (long i = 0; i < map.size(); i++) {
            Client foundClient = map.get(i);
            if (client.equals(foundClient)) {
                System.out.println("Remove client: " + client.getName());
                map.remove(i);
                break;
            }
            ;
        }
    }

    public static ClientDao getInstance() {
        if (clientDao == null) {
            clientDao = new ClientDaoImpl();
        }
        return clientDao;
    }
}
