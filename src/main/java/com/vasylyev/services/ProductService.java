package com.vasylyev.services;

import com.vasylyev.domain.Product;

import java.math.BigDecimal;
import java.util.List;

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
    List<Product> GetAllProducts();

}
