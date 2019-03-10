package com.vasylyev.services.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.dao.OrderDao;
import com.vasylyev.dao.ProductDao;
import com.vasylyev.domain.Client;
import com.vasylyev.domain.Order;
import com.vasylyev.domain.Product;
import com.vasylyev.services.OrderService;

import java.util.ArrayList;
import java.util.List;

import static com.vasylyev.services.impl.ClientServiceImpl.showClient;
import static com.vasylyev.services.impl.ProductServiceImpl.showProduct;

public class OrderServiceImpl implements OrderService {

    private ClientDao clientDao;
    private ProductDao productDao;
    private OrderDao orderDao;

    public OrderServiceImpl(ClientDao clientDao, ProductDao productDao, OrderDao orderDao) {
        this.orderDao = orderDao;
        this.clientDao = clientDao;
        this.productDao = productDao;
    }

    @Override
    public void createOrder(Long id, List<String> productNameList) {
        Client client = clientDao.findClient(id);
        showClient(id, client);
        if (client != null) {
            ArrayList<Product> productListOrder = new ArrayList<Product>();
            if (productNameList != null) {
                for (String prName : productNameList) {
                    Product tempProduct = productDao.findProduct(prName);
                    showProduct(prName, tempProduct);
                    if (tempProduct != null) {
                        productListOrder.add(tempProduct);
                    }
                }
            }
            Order order = new Order(client, productListOrder);
            orderDao.saveOrder(order);
        }
    }

    @Override
    public Order findOrder(Long id) {
        Order tempOrder = orderDao.findOrder(id);
        showOrder(id, tempOrder);
        return tempOrder;
    }

    @Override
    public void deleteOrder(Long id) {
        Order tempOrder = findOrder(id);
        if (tempOrder != null) {
            orderDao.deleteOrder(tempOrder);
        }
    }

    @Override
    public List<Order> GetAllOrders() {
        return orderDao.getOrderList();
    }

    private void showOrder(Long id, Order order){
        String orderResult;
        if (order != null) {
            orderResult = "Found order: " + order;
        }
        else {
            orderResult = "Order not found: " + id;
        }
        System.out.println(orderResult);
    }
}
