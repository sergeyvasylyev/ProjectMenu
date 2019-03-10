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
        setStatement(ProductSQLInsert, product, "");
    }

    @Override
    public Product findProduct(String productName) {
        return findProductCommon(ProductSQLFindName, productName);
    }

    @Override
    public Product findProduct(Long id) {
        return findProductCommon(ProductSQLFindName, id);
    }

    @Override
    public void modifyProduct(Product product, String newName) {
        setStatement(ProductSQLUpdate, product, newName);
    }

    @Override
    public void deleteProduct(Product product) {
        setStatement(ProductSQLUpdate, product, "");
    }

    @Override
    public List<Product> getProductList() {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(ProductQLGetList);) {
            try (ResultSet resultSet = statement.executeQuery();) {
                List<Product> result = new ArrayList<>();
                while (resultSet.next()) {
                    result.add(getProductRomRS(resultSet));
                }
                return result;
            }
        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }
        return null;
    }


    private void setStatement(String sqlSt, Product product, String newName){
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlSt);) {
            switch (sqlSt) {
                case ProductSQLUpdate:
                    statement.setString(1, newName);
                    statement.setLong(2, product.getId());
                    break;
                case ProductSQLDelete:
                    statement.setLong(1, product.getId());
                    break;
                case ProductSQLInsert:
                    statement.setString(1, product.getName());
                    statement.setBigDecimal(2, product.getPrice());
                    break;
            }
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }
    }

    private <T> Product findProductCommon(String sqlSt, T param){
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlSt);) {
            switch (sqlSt){
                case ProductSQLFindId:
                    statement.setLong(1, (Long) param);
                    break;
                case ProductSQLFindName:
                    statement.setString(1, (String)param);
                    break;
            }
            try (ResultSet resultSet = statement.executeQuery();) {
                if (resultSet.next()) {
                    return getProductRomRS(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }
        return null;
    }

    static Product getProductRomRS(ResultSet resultSet) throws SQLException{
        long currentId = resultSet.getLong(1);
        String name = resultSet.getString(2);
        BigDecimal price = resultSet.getBigDecimal(3);
        return new Product(currentId, name, price);
    }
}
