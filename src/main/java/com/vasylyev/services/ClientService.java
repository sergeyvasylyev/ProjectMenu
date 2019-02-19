package com.vasylyev.services;

import com.vasylyev.domain.Client;

import java.util.List;

public interface ClientService {

    /*
     * add client with name, surname, phone
     * */
    void createClient(String name, String surname, String phone);

    void createClient(String name, String surname, int age, String email, String phone);

    /*
     * modify client with name
     * */
    void modifyClient(Long id, String newName);

    /*
     * delete client with name
     * */
    void deleteClient(Long id);

    /*
     * get clients list
     * */
    List<Client> GetAllClients();
}
