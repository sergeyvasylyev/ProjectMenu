package com.vasylyev.dao.impl;

import com.vasylyev.dao.ProductDao;
import com.vasylyev.domain.Product;

import java.util.List;

public class ProductDaoImpl implements ProductDao {


    public void saveProduct(Product product) {
        product.setId(getMaxId() + 1);
        System.out.println("Save product: "+product.getName());
        productList.add(product);
    }

    public Product findProduct(String productName){
        for (int i = 0; i < productList.size(); i++) {
            Product foundProduct = productList.get(i);
            if (productName.equals(foundProduct.getName())){
                return foundProduct;
            };
        }
        return null;
    }

    public void modifyProduct(Product product, String newName){
        product.setName(newName);
        System.out.println("Save product: "+product.getName());
    }

    public List<Product> getProductList(){
        return productList;
    }

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
