package com.vasylyev.services;

import com.vasylyev.domain.Client;

import java.util.List;

public interface ClientService {


    /*
    * add client with name, surname, phone
    * */
    void createClient(String name, String surname, String phone);

    void createClient(String name, String surname, int age, String phone, String email);

    /*
     * modify client with name
     * */
    void modifyClient(String name, String newName);

    /*
     * delete client with name
     * */
    void deleteClient(String name);

    /*
     * get clients list
     * */
    void getClientsList();

    /*
    *
    *  */
    List<Client> GetAllClients();
}
