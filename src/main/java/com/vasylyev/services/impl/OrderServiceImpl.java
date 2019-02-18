package com.vasylyev.services.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.dao.OrderDao;
import com.vasylyev.dao.ProductDao;
import com.vasylyev.dao.impl.ClientDaoImpl;
import com.vasylyev.dao.impl.OrderDaoImpl;
import com.vasylyev.dao.impl.ProductDaoImpl;
import com.vasylyev.domain.Client;
import com.vasylyev.domain.Order;
import com.vasylyev.domain.Product;
import com.vasylyev.services.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao = new OrderDaoImpl();
    //private final ClientDao clientDao = new ClientDaoImpl();
    private ClientDao clientDao = ClientDaoImpl.getInstance();
    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public void createOrder(String clientName, List<String> productNameList){
        Client client = clientDao.findClient(clientName);
        System.out.println("Found client: " + client);
        if (client != null) {
            ArrayList<Product> productListOrder = new ArrayList<Product>();
            if (productNameList != null) {
                for (int i = 0; i < productNameList.size(); i++) {
                    Product tempProduct = productDao.findProduct(productNameList.get(i));
                    if (tempProduct != null) {
                        productListOrder.add(tempProduct);
                    }
                    System.out.println("Found product to create order: " + tempProduct);
                }
            }
            Order order = new Order(client, productListOrder);
            orderDao.saveOrder(order);
        }
    }

    @Override
    public void findOrder(Long id) {
        Order tempOrder = orderDao.findOrder(id);
        System.out.println("Found order: " + tempOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        Order tempOrder = orderDao.findOrder(id);
        System.out.println("Found order: " + tempOrder);
        if (tempOrder != null){
            orderDao.deleteOrder(tempOrder);
        }
    }

    @Override
    public void getOrdersList() {
        List<Order> orderList = orderDao.getOrderList();
        for (int i = 0; i < orderList.size(); i++) {
            System.out.println(orderList.get(i).toString());
        }
        System.out.println("Number of orders: " + orderList.size());
    }
}
