package com.vasylyev.services;

import java.math.BigDecimal;

public interface ProductService {

    /*
     * add Product with name, price
     * */
    void createProduct(String name, BigDecimal price);

    /*
     * modify Product with name
     * */
    void modifyProduct(String name, String newName);

    /*
     * delete Product with name
     * */
    void deleteProduct(String name);

    /*
     * find Product with name
     * */
    void findProduct(String name);

    /*
     * get Product list
     * */
    void getProductsList();

}
