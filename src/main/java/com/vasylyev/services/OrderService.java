package com.vasylyev.services;

import com.vasylyev.domain.Client;

import java.util.List;

public interface OrderService {

    /*
     * add Order with client and empty product list
     * */
    void createOrder(Client client);

    /*
     * add Order clientName
     * */
    void createOrder(String clientName);

    /*
     * add Order with client and list of product names
     * */
    void createOrder(Client client, List<String> productNameList);

    /*
     * add Order with clientName and list of product names
     * */
    void createOrder(String clientName, List<String> productNameList);


    /*
     * modify Order with name
     * */
    void modifyOrder(String number, String newNumber);

    /*
     * delete Order with name
     * */
    void deleteOrder(Long id);

    /*
     * find Order with name
     * */
    void findOrder(Long id);

    /*
     * get Order list
     * */
    void getOrdersList();


}
