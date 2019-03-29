package com.vasylyev.services.impl;

import com.vasylyev.dao.ProductDao;
import com.vasylyev.domain.Product;
import com.vasylyev.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public void createProduct(String name, BigDecimal price) {
        Product product = new Product(name, price);
        productDao.saveProduct(product);
    }

    @Override
    public void modifyProduct(String name, String newName) {
        Product tempProduct = findProduct(name);
        if (tempProduct != null) {
            productDao.modifyProduct(tempProduct, newName);
        }
    }

    @Override
    public void deleteProduct(String name) {
        Product tempProduct = findProduct(name);
        if (tempProduct != null) {
            productDao.deleteProduct(tempProduct);
        }
    }

    @Override
    public Product findProduct(String name) {
        Product tempProduct = productDao.findProduct(name);
        showProduct(name, tempProduct);
        return tempProduct;
    }

    @Override
    public List<Product> GetAllProducts() {
        return productDao.getProductList();
    }

    static void showProduct(String name, Product product){
        String productResult;
        if (product != null) {
            productResult = "Found product: " + product;
        }
        else {
            productResult = "Product not found: " + name;
        }
        System.out.println(productResult);
    }

}
