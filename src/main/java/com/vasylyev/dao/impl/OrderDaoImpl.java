package com.vasylyev.dao.impl;

import com.vasylyev.dao.OrderDao;
import com.vasylyev.domain.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private List<Order> orderList = new ArrayList<>();

    @Override
    public void saveOrder(Order order) {
        order.setId(getMaxId() + 1);
        System.out.println("Save order: "+order.getId());
        orderList.add(order);
    }

    @Override
    public Order findOrder(Long id) {
        for (int i = 0; i < orderList.size(); i++) {
            Order foundOrder = orderList.get(i);
            if (id == foundOrder.getId()){
                return foundOrder;
            };
        }
        return null;
    }

    @Override
    public List<Order> getOrderList() {
        return orderList;
    }

    @Override
    public void deleteOrder(Order order) {
        for (int i = 0; i < orderList.size(); i++) {
            Order foundOrder = orderList.get(i);
            if (order.equals(foundOrder)){
                System.out.println("Remove order: "+order.getId());
                orderList.remove(i);
                break;
            };
        }
    }

    private long getMaxId(){
        long maxId = 0;
        for (int i = 0; i < orderList.size(); i++) {
            Order foundOrder = orderList.get(i);
            if (foundOrder.getId() > maxId){
                maxId = foundOrder.getId();
            }
        }
        return maxId;
    }
}
