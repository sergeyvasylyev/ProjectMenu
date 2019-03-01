package com.vasylyev.dao.impl;

import com.vasylyev.dao.ProductDao;
import com.vasylyev.domain.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.vasylyev.dao.impl.CommonDBDao.*;

public class ProductDBDao implements ProductDao {

    public ProductDBDao() {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            statement.execute(ProductSQLConstructor);
        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }
    }

    @Override
    public void saveProduct(Product product) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(ProductSQLInsert);
        ) {
            statement.setString(1, product.getName());
            statement.setBigDecimal(2, product.getPrice());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }
    }

    @Override
    public Product findProduct(String productName) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(ProductSQLFindName);) {
            statement.setString(1, productName);
            try (ResultSet resultSet = statement.executeQuery();) {
                if (resultSet.next()) {
                    long currentId = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    BigDecimal price = resultSet.getBigDecimal(3);
                    return new Product(currentId, name, price);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }
        return null;
    }

    public Product findProduct(Long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(ProductSQLFindId);) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery();) {
                if (resultSet.next()) {
                    long currentId = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    BigDecimal price = resultSet.getBigDecimal(3);
                    return new Product(id, name, price);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }
        return null;
    }

    @Override
    public void modifyProduct(Product product, String newName) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(ProductSQLUpdate);) {
            statement.setString(1, newName);
            statement.setLong(2, product.getId());
            statement.execute();

        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }
    }

    @Override
    public List<Product> getProductList() {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(ProductQLGetList);) {
            try (ResultSet resultSet = statement.executeQuery();) {
                List<Product> result = new ArrayList<>();
                while (resultSet.next()) {
                    long currentId = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    BigDecimal price = resultSet.getBigDecimal(3);

                    result.add(new Product(currentId, name, price));
                }
                return result;
            }

        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }

        return null;
    }

    @Override
    public void deleteProduct(Product product) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(ProductSQLDelete);) {
            statement.setLong(1, product.getId());
            statement.execute();

        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }
    }
}
