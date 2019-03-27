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
    public Product findProduct(Long id) {
        entityManager.getTransaction().begin();
        Product product = entityManager.find(Product.class, id);
        entityManager.getTransaction().commit();
        return product;
    }

    @Override
    public Product findProduct(String productName) {
        entityManager.getTransaction().begin();
        String hql = "from Product where name = :name";
        Product product = entityManager.createQuery(hql,Product.class)
                .setParameter("name", productName)
                .getResultList()
                .stream().findFirst()
                .orElse(null);
        entityManager.getTransaction().commit();
        return product;
    }

    @Override
    public void modifyProduct(Product product, String newName) {
        entityManager.getTransaction().begin();
        product.setName(newName);
        entityManager.persist(product);
        entityManager.getTransaction().commit();
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
        entityManager.getTransaction().begin();
        entityManager.remove(product);
        entityManager.getTransaction().commit();
    }
}
