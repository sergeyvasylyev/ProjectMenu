package com.vasylyev.dao;

import com.vasylyev.domain.Client;

import java.util.ArrayList;
import java.util.List;

public interface ClientDao {

    //to save clients list
    List<Client> clientList = new ArrayList();

    /*
    * save Client by link
    * */
    void saveClient(Client client);

    /*
    * find first client with name "clientName"
    * return Client
    * */
    Client findClient(String clientName);

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
