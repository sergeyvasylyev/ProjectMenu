package com.vasylyev.dao.impl;

import com.vasylyev.dao.ProductDao;
import com.vasylyev.domain.Product;

public class ProductDaoImpl implements ProductDao {

    @Override
    public void saveProduct(Product product) {
        System.out.println("Save product: " + product.getName());
    }
}
