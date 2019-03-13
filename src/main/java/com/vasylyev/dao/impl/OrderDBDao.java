package com.vasylyev.dao.impl;

import com.vasylyev.dao.OrderDao;
import com.vasylyev.domain.Client;
import com.vasylyev.domain.Order;
import com.vasylyev.domain.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.vasylyev.dao.impl.CommonDBDao.*;


public class OrderDBDao implements OrderDao {

    public OrderDBDao() {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            statement.execute(OrderSQLConstructor);
        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }
    }

    @Override
    public void saveOrder(Order order) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statementOD = connection.prepareStatement(OrderSQLInsertOD);
             Statement statementMaxId = connection.createStatement();
             PreparedStatement statementOP = connection.prepareStatement(OrderSQLInsertOP);
        ) {
            statementOD.setLong(1, order.getClient().getId());
            statementOD.execute();
            long OrderId = -1;
            try (ResultSet resultSet = statementMaxId.executeQuery(OrderSQLMaxId)) {
                if (resultSet.next()) {
                    OrderId = resultSet.getLong(1);
                }
            }
            if (OrderId != -1) {
                for (Product product : order.getProducts()) {
                    statementOP.setLong(1, OrderId);
                    statementOP.setLong(2, product.getId());
                    statementOP.execute();
                }
            }

        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }
    }

    @Override
    public Order findOrder(Long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(OrderSQLFind);) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery();) {
                List<Product> productList = new ArrayList<>();
                long orderId = -1;
                long clientId = -1;
                String clientName = "";
                String clientPhone = "";
                while (resultSet.next()) {
                    if (orderId == -1) {
                        orderId = resultSet.getLong("odId");
                        clientId = resultSet.getLong("cId");
                        clientName = resultSet.getString("cName");
                        clientPhone = resultSet.getString("cPhone");
                    }
                    productList.add(getProductRomRS(resultSet));
                }
                if (orderId != -1) {
                    return new Order(orderId, new Client(clientId, clientName, clientPhone), productList);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Order> getOrderList() {

        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement();) {
            try (ResultSet resultSet = statement.executeQuery(OrderSQLGetList);) {
                List<Order> result = new ArrayList<>();
                List<Product> productList = new ArrayList<>();
                long orderId = -1;
                long nextOrderId = -1;
                long clientId = -1;
                String clientName = "";
                String clientPhone = "";
                while (resultSet.next()) {
                    nextOrderId = resultSet.getLong("odId");
                    if (orderId != nextOrderId && orderId != -1)//next order
                    {
                        result.add(new Order(orderId, new Client(clientId, clientName, clientPhone), productList));
                        productList = new ArrayList<>();
                    }
                    productList.add(getProductRomRS(resultSet));

                    orderId = nextOrderId;

                    clientId = resultSet.getLong("cId");
                    clientName = resultSet.getString("cName");
                    clientPhone = resultSet.getString("cPhone");
                }
                if (clientId != -1) {
                    result.add(new Order(orderId, new Client(clientId, clientName, clientPhone), productList));
                }
                return result;
            }

        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }

        return null;
    }

    @Override
    public void deleteOrder(Order order) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statementOD = connection.prepareStatement(OrderSQLDeleteOD);
             PreparedStatement statementOP = connection.prepareStatement(OrderSQLDeleteOP);) {
            statementOD.setLong(1, order.getId());
            statementOD.execute();
            statementOP.setLong(1, order.getId());
            statementOP.execute();

        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }
    }

    private Product getProductRomRS(ResultSet resultSet) throws SQLException{
        long currentId = resultSet.getLong("pId");
        String name = resultSet.getString("pName");
        BigDecimal price = resultSet.getBigDecimal("pPrice");
        return new Product(currentId, name, price);
    }
}
