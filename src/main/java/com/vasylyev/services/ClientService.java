package com.vasylyev.services;

public interface ClientService {
    /*
    * add client with name, surname, phone
    * */
    void createClient(String name, String surname, String phone);

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

}
