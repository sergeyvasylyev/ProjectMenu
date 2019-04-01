package com.vasylyev.dao.impl;

import com.vasylyev.dao.OrderDao;
import com.vasylyev.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Component
@Primary
public class OrderEMDao implements OrderDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void saveOrder(Order order) {
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
    }

    @Override
    public Order findOrder(Long id) {
        entityManager.getTransaction().begin();
        Order order = entityManager.find(Order.class, id);
        entityManager.getTransaction().commit();
        return order;
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
        entityManager.getTransaction().begin();
        entityManager.remove(order);
        entityManager.getTransaction().commit();
    }
}
