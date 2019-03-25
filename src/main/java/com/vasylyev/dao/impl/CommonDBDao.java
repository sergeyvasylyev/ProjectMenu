package com.vasylyev.dao.impl;

import com.vasylyev.domain.Product;

import java.sql.*;

public class CommonDBDao {

    //public static final String DB_URL = "jdbc:h2:./LuxoftShop";
    public static final String DB_URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    public static final String LOGIN = "test";
    public static final String PASSWORD = "test";

    //SQL Queries
    //client
    public static final String ClientSQLConstructor = "CREATE TABLE IF NOT EXISTS client (ID INT AUTO_INCREMENT, NAME VARCHAR(50), SURNAME VARCHAR(50), AGE INT, PHONE VARCHAR(20), EMAIL VARCHAR(50))";
    public static final String ClientSQLInsert = "insert into client (name, surname, age, phone, email) values (?,?,?,?,?)";
    public static final String ClientSQLFindId = "select * from client where id = ?";
    public static final String ClientSQLFindPhone = "select * from client where phone = ?";
    public static final String ClientSQLUpdate = "update client set name = ? where id = ?";
    public static final String ClientSQLGetList = "select id, name, surname, age, phone, email from client order by id";
    public static final String ClientSQLDelete = "delete from client where id = ?";

    //product
    public static final String ProductSQLConstructor = "CREATE TABLE IF NOT EXISTS product (ID INT AUTO_INCREMENT, NAME VARCHAR(50), PRICE DECIMAL(10,2))";
    public static final String ProductSQLInsert = "insert into product (name, price) values (?,?)";
    public static final String ProductSQLFindName = "select * from product where name = ?";
    public static final String ProductSQLFindId = "select * from product where id = ?";
    public static final String ProductSQLUpdate = "update product set name = ? where id = ?";
    public static final String ProductQLGetList = "select * from product order by id";
    public static final String ProductSQLDelete = "delete from product where name = ?";

    //order
    public static final String OrderSQLConstructor = "CREATE TABLE IF NOT EXISTS OrderDocument (id int AUTO_INCREMENT, ClientId int); " +
            "CREATE TABLE  IF NOT EXISTS OrderProduct (id int AUTO_INCREMENT, OrderId int, ProductId int)";
    public static final String OrderSQLInsertOD = "insert into OrderDocument (ClientId) values (?)";
    public static final String OrderSQLInsertOP = "insert into OrderProduct (OrderId, ProductId) values (?,?)";
    public static final String OrderSQLMaxId = "select max(id) from OrderDocument";
    public static final String OrderSQLFind = "select od.id as odId, od.ClientId as cId, c.name as cName, c.phone as cPhone, op.productId as pId, p.name as pName, p.price as pPrice\n" +
            "from OrderDocument as od \n" +
            "join OrderProduct as op on od.id = op.orderId \n" +
            "join Product as p on p.id = op.productId\n" +
            "join Client as c on c.id = od.clientId\n" +
            "where od.id = ?\n" +
            "order by od.id";
    public static final String OrderSQLGetList = "select od.id as odId, od.ClientId as cId, c.name as cName, c.phone as cPhone, op.productId as pId, p.name as pName, p.price as pPrice\n" +
            "from OrderDocument as od \n" +
            "join OrderProduct as op on od.id = op.orderId \n" +
            "join Product as p on p.id = op.productId\n" +
            "join Client as c on c.id = od.clientId\n" +
            "order by od.id";
    public static final String OrderSQLDeleteOD = "delete from OrderDocument where id = ?;";
    public static final String OrderSQLDeleteOP = "delete from OrderProduct where orderId = ?";

}
