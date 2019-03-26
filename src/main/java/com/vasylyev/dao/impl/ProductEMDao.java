package com.vasylyev.dao.impl;

import com.vasylyev.dao.ProductDao;
import com.vasylyev.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ProductEMDao implements ProductDao {

    private EntityManager entityManager;

    public ProductEMDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public void saveProduct(Product product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    @Override
    public Product findProduct(String productName) {
        return null;
    }

    @Override
    public Product findProduct(Long id) {
        return null;
    }

    @Override
    public void modifyProduct(Product product, String newName) {

    }

    @Override
    public List<Product> getProductList() {
        entityManager.getTransaction().begin();
        List<Product> resultList = entityManager.createQuery("from Product order by id", Product.class).getResultList();
        entityManager.getTransaction().commit();
        return resultList;
    }

    @Override
    public void deleteProduct(Product product) {

    }
}
