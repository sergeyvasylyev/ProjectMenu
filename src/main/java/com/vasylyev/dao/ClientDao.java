package com.vasylyev.dao;

import com.vasylyev.domain.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ClientDao {
    /*
     * save Client by link
     * */
    void saveClient(Client client);

    /*
     * find first client with name "clientName"
     * return Client
     * */
    Client findClient(Long id);

    /*
    * find client by phone number
    * use this method for validation
    * */
    Client findClient(String phoneNumber);

    /*
     * edit Client by link. Set up new name in newName
     * */
    void modifyClient(Client client, String newName);

    /*
     * return list of Clients
     * */
    List<Client> getClientsList();

    /*
     * delete Client by link
     * */
    void deleteClient(Client client);
}
