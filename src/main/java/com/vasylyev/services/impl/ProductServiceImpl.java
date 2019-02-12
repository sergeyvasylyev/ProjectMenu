package com.vasylyev.services.impl;

import com.vasylyev.domain.Product;
import com.vasylyev.services.ProductService;
import java.math.BigDecimal;

public class ProductServiceImpl implements ProductService {

    @Override
    public void createProduct(String name, BigDecimal price) {
        Product product = new Product(name, price);
    }

}
