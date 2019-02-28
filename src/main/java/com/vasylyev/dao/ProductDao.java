package com.vasylyev.dao;

import com.vasylyev.domain.Product;
import java.util.ArrayList;
import java.util.List;

public interface ProductDao {
    /*
     * save product by link
     * */
    void saveProduct(Product product);

    /*
     * find first product with name "productName"
     * return product
     * */
    Product findProduct(String productName);

    /*
     * find product by id
     * return product
     * */
    Product findProduct(Long id);

    /*
     * edit product by link. Set up new name in newName
     * */
    void modifyProduct(Product product, String newName);

    /*
     * return list of product
     * */
    List<Product> getProductList();

    /*
     * delete product by link
     * */
    void deleteProduct(Product product);

}
