package com.vasylyev.dao.impl;

import com.vasylyev.dao.ProductDao;
import com.vasylyev.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDaoImpl implements ProductDao {

    private static ProductDao productDao;

    private Map<Long, Product> map = new HashMap<>();
    private static long generator = 0;

    private ProductDaoImpl() {

    }

    @Override
    public void saveProduct(Product product) {
        product.setId(generator++);
        System.out.println("Save product: " + product.getName());
        map.put(product.getId(), product);
    }

    @Override
    public Product findProduct(String productName) {
        for (long i = 0; i < map.size(); i++) {
            Product foundProduct = map.get(i);
            if (foundProduct != null) {
                if (productName.equals(foundProduct.getName())) {
                    return foundProduct;
                }
            }
            ;
        }
        return null;
    }

    @Override
    public void modifyProduct(Product product, String newName) {
        product.setName(newName);
        System.out.println("Save product: " + product.getName());
    }

    @Override
    public List<Product> getProductList() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void deleteProduct(Product product) {
        for (long i = 0; i < map.size(); i++) {
            Product foundProduct = map.get(i);
            if (product.equals(foundProduct)) {
                System.out.println("Remove product: " + product.getName());
                map.remove(i);
                break;
            }
            ;
        }
    }

    public static ProductDao getInstance() {
        if (productDao == null) {
            productDao = new ProductDaoImpl();
        }
        return productDao;
    }

}
