package com.vasylyev.dao;

import com.vasylyev.domain.Order;
import java.util.ArrayList;
import java.util.List;

public interface OrderDao {

    /*
     * to save order list
     * */
    List<Order> orderList = new ArrayList();

    /*
     * save Order by link
     * */
    void saveOrder(Order order);

    /*
     * find first Order with name "orderNumber"
     * return Order
     * */
    Order findOrder(Long id);

    /*
     * return list of Orders
     * */
    List<Order> getOrderList();

    /*
     * delete Order by link
     * */
    void deleteOrder(Order order);
}
