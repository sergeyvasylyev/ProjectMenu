package com.vasylyev.services.impl;

import com.vasylyev.dao.ProductDao;
import com.vasylyev.dao.impl.ProductDaoImpl;
import com.vasylyev.domain.Product;
import com.vasylyev.services.ProductService;
import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public void createProduct(String name, BigDecimal price) {
        Product product = new Product(name, price);
        productDao.saveProduct(product);
    }

    @Override
    public void modifyProduct(String name, String newName) {
        Product tempProduct = productDao.findProduct(name);
        System.out.println("Found product: " + tempProduct);
        if (tempProduct != null){
            productDao.modifyProduct(tempProduct, newName);
        }
    }

    @Override
    public void deleteProduct(String name) {
        Product tempProduct = productDao.findProduct(name);
        System.out.println("Found product: " + tempProduct);
        if (tempProduct != null){
            productDao.deleteProduct(tempProduct);
        }
    }

    @Override
    public void findProduct(String name) {
        Product tempProduct = productDao.findProduct(name);
        System.out.println("Found product: " + tempProduct);
    }

    @Override
    public void getProductsList(){
        List<Product> productList = productDao.getProductList();
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i).toString());
        }
        System.out.println("Number of products: " + productList.size());
    }

}
