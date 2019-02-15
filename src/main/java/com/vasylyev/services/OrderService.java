package com.vasylyev.services;

import com.vasylyev.domain.Client;

import java.util.List;

public interface OrderService {

    /*
     * add Order with clientName and list of product names
     * */
    void createOrder(String clientName, List<String> productNameList);

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
