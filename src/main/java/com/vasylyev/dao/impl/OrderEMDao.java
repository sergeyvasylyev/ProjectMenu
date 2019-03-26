package com.vasylyev.dao.impl;

import com.vasylyev.dao.OrderDao;
import com.vasylyev.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class OrderEMDao implements OrderDao {

    private EntityManager entityManager;

    public OrderEMDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public void saveOrder(Order order) {

    }

    @Override
    public Order findOrder(Long id) {
        return null;
    }

    @Override
    public List<Order> getOrderList() {
        entityManager.getTransaction().begin();
        List<Order> resultList = entityManager.createQuery("from Order", Order.class).getResultList();
        entityManager.getTransaction().commit();
        return resultList;
    }

    @Override
    public void deleteOrder(Order order) {

    }
}
