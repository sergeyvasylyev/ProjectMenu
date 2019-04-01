package com.vasylyev.dao.impl;

import com.vasylyev.dao.OrderDao;
import com.vasylyev.domain.Order;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {

    private static OrderDao orderDao;

    private Map<Long, Order> map = new HashMap<>();
    private static long generator = 0;

    private OrderDaoImpl() {
    }

    @Override
    public void saveOrder(Order order) {
        order.setId(generator++);
        System.out.println("Save order: " + order.getId());
        map.put(order.getId(), order);
    }

    @Override
    public Order findOrder(Long id) {
        for (long i = 0; i < map.size(); i++) {
            Order foundOrder = map.get(i);
            if (id == foundOrder.getId()) {
                return foundOrder;
            }
            ;
        }
        return null;
    }

    @Override
    public List<Order> getOrderList() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void deleteOrder(Order order) {
        for (long i = 0; i < map.size(); i++) {
            Order foundOrder = map.get(i);
            if (order.equals(foundOrder)) {
                System.out.println("Remove order: " + order.getId());
                map.remove(i);
                break;
            }
            ;
        }
    }

    public static OrderDao getInstance() {
        if (orderDao == null) {
            orderDao = new OrderDaoImpl();
        }
        return orderDao;
    }
}
