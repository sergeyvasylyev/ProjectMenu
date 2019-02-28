package com.vasylyev.dao.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.domain.Client;

import java.sql.*;
import java.util.List;

public class ClientDBDao implements ClientDao {

    public static final String DB_URL = "jdbc:h2:./LuxoftShop";
    public static final String LOGIN = "test";
    public static final String PASSWORD = "test";

    public ClientDBDao() {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement();
             )
        {

            //тут надо создать таблицу с проверкой существует или нет
            //statement.executeQuery("insert into client (name, surname,age,phone,email) values ( 'test', 'test', 20,'0502629760', 'wwww@www.ww')");
            //create table client (ID INT AUTO_INCREMENT, NAME VARCHAR(50), SURNAME VARCHAR(50), PHONE VARCHAR(20), AGE INT, EMAIL VARCHAR(50))
            statement.execute("select * from client");
            //statement.execute("create table client (ID INT AUTO_INCREMENT, NAME VARCHAR(50), SURNAME VARCHAR(50), PHONE VARCHAR(20), AGE INT, EMAIL VARCHAR(50))");


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error with DB!");
        }
    }

    @Override
    public void saveClient(Client client) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             //Statement statement = connection.createStatement();
             PreparedStatement statement = connection.prepareStatement(
                     "insert into client (name, surname, age, phone, email) values (?,?,?,?,?)");
             ){
                statement.setString(1,client.getName());
                statement.setString(2,client.getSurname());
                statement.setInt(3,client.getAge());
                statement.setString(4,client.getPhone());
                statement.setString(5,client.getEmail());
                statement.execute();

                //return statement.execute();
/*
         statement.execute("insert into client (name, surname,age,phone,email) values ( '"
                 +client.getName()+"', '"
                 +client.getSurname()+"', "
                 +client.getAge()+",'"
                 +client.getPhone()+"', '"
                 +client.getEmail()+"')");

*/
        } catch (SQLException e) {
            System.out.println("Error with DB!");
        }
    }

    @Override
    public Client findClient(Long id) {

        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement();){//переписать через preparedstatement

            ResultSet resultSet = statement.executeQuery("select * from client where id = '"+id+"'");
            if (resultSet.next()) //позиционируемся на первой строке набора результирующих записей
            {
                long currentId = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString("SURNAME");
                int age = resultSet.getInt("AGE");
                String phone = resultSet.getString(5);
                String email = resultSet.getString(6);
                resultSet.close();

                return new Client(id, name, surname, age, phone, email);
            }

        } catch (SQLException e) {
            System.out.println("Error with DB!");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Client findClient(String phoneNumber) {
        return null;
    }

    @Override
    public void modifyClient(Client client, String newName) {

    }

    @Override
    public List<Client> getClientsList() {
        return null;
    }

    @Override
    public void deleteClient(Client client) {

    }
}
