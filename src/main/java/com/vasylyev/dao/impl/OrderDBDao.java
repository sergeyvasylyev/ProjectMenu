package com.vasylyev.dao.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.dao.OrderDao;
import com.vasylyev.dao.ProductDao;
import com.vasylyev.domain.Client;
import com.vasylyev.domain.Order;
import com.vasylyev.domain.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDBDao implements OrderDao {

    //public static final String DB_URL = "jdbc:h2:./LuxoftShop";
    public static final String DB_URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    public static final String LOGIN = "test";
    public static final String PASSWORD = "test";

    private ProductDao productDao;
    private ClientDao clientDao;

    public OrderDBDao() {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement();
        )
        {
            statement.execute("CREATE TABLE IF NOT EXISTS OrderDocument (id int AUTO_INCREMENT, ClientId int); " +
                    "CREATE TABLE  IF NOT EXISTS OrderProduct (id int AUTO_INCREMENT, OrderId int, ProductId int)");

        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! "+e.getMessage());
        }
    }

    @Override
    public void saveOrder(Order order) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statementOD = connection.prepareStatement("insert into OrderDocument (ClientId) values (?)");
             Statement statementMaxId = connection.createStatement();
             PreparedStatement statementOP = connection.prepareStatement("insert into OrderProduct (OrderId, ProductId) values (?,?)");
        ){
            statementOD.setLong(1,order.getClient().getId());
            statementOD.execute();
            long OrderId = -1;
            try (ResultSet resultSet = statementMaxId.executeQuery("select max(id) from OrderDocument")) {
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
            System.out.println("Error with SQL Connection! "+e.getMessage());
        }
    }

    @Override
    public Order findOrder(Long id) {
        return null;
    }

    @Override
    public List<Order> getOrderList() {

        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement();)
        {
            try (ResultSet resultSet = statement.executeQuery(
                    "select od.id as odId, od.ClientId as cId, c.name as cName, c.phone as cPhone, op.productId as pId, p.name as pName, p.price as pPrice\n" +
                            "from OrderDocument as od \n" +
                            "join OrderProduct as op on od.id = op.orderId \n" +
                            "join Product as p on p.id = op.productId\n" +
                            "join Client as c on c.id = od.clientId\n" +
                            "order by od.id");)
            {
                List<Order> result = new ArrayList<>();
                List<Product> productList = new ArrayList<>();
                long orderId = -1;
                long nextOrderId = -1;
                long clientId = -1;
                String clientName = "";
                String clientPhone = "";
                while (resultSet.next())
                {
                    nextOrderId = resultSet.getLong("odId");
                    if (orderId != nextOrderId && orderId != -1)//next order
                    {
                        result.add(new Order(orderId, new Client(clientId, clientName, clientPhone), productList));
                        //productList.clear();
                        productList = new ArrayList<>();
                    }
                    long productId = resultSet.getLong("pId");
                    String productName = resultSet.getString("pName");
                    BigDecimal productPrice = resultSet.getBigDecimal("pPrice");
                    productList.add(new Product(productId, productName, productPrice));
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
            System.out.println("Error with SQL Connection! "+e.getMessage());
        }

        return null;
    }

    @Override
    public void deleteOrder(Order order) {

    }
}
