package com.vasylyev.dao.impl;

import com.vasylyev.dao.ProductDao;
import com.vasylyev.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private List<Product> productList = new ArrayList<>();

    @Override
    public void saveProduct(Product product) {
        product.setId(getMaxId() + 1);
        System.out.println("Save product: "+product.getName());
        productList.add(product);
    }

    @Override
    public Product findProduct(String productName){
        for (int i = 0; i < productList.size(); i++) {
            Product foundProduct = productList.get(i);
            if (productName.equals(foundProduct.getName())){
                return foundProduct;
            };
        }
        return null;
    }

    @Override
    public void modifyProduct(Product product, String newName){
        product.setName(newName);
        System.out.println("Save product: "+product.getName());
    }

    @Override
    public List<Product> getProductList(){
        return productList;
    }

    @Override
    public void deleteProduct(Product product){
        for (int i = 0; i < productList.size(); i++) {
            Product foundProduct = productList.get(i);
            if (product.equals(foundProduct)){
                System.out.println("Remove product: "+product.getName());
                productList.remove(i);
                break;
            };
        }
    }

    private long getMaxId(){
        long maxId = 0;
        for (int i = 0; i < productList.size(); i++) {
            Product foundProduct = productList.get(i);
            if (foundProduct.getId() > maxId){
                maxId = foundProduct.getId();
            }
        }
        return maxId;
    }
}
