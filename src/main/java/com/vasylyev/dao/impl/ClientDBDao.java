package com.vasylyev.dao.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.domain.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDBDao implements ClientDao {

    //public static final String DB_URL = "jdbc:h2:./LuxoftShop";
    public static final String DB_URL = "jdbc:h2:tcp://localhost/~/LuxoftShop";
    public static final String LOGIN = "test";
    public static final String PASSWORD = "test";

    public ClientDBDao() {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement();
        )
        {
            statement.execute("CREATE TABLE IF NOT EXISTS client (ID INT AUTO_INCREMENT, NAME VARCHAR(50), SURNAME VARCHAR(50), PHONE VARCHAR(20), AGE INT, EMAIL VARCHAR(50))");

        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! "+e.getMessage());
        }
    }

    @Override
    public void saveClient(Client client) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     "insert into client (name, surname, age, phone, email) values (?,?,?,?,?)");
        ){
            statement.setString(1,client.getName());
            statement.setString(2,client.getSurname());
            statement.setInt(3,client.getAge());
            statement.setString(4,client.getPhone());
            statement.setString(5,client.getEmail());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! "+e.getMessage());
        }
    }

    @Override
    public Client findClient(Long id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("select * from client where id = ?");)
        {
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int age = resultSet.getInt(4);
                String phone = resultSet.getString(5);
                String email = resultSet.getString(6);
                resultSet.close();

                return new Client(id, name, surname, age, phone, email);
            }

        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! "+e.getMessage());
        }

        return null;
    }

    @Override
    public Client findClient(String phoneNumber) {

        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("select * from client where phone = ?");)
        {
            statement.setString(1,phoneNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                long currentId = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int age = resultSet.getInt(4);
                String phone = resultSet.getString(5);
                String email = resultSet.getString(6);
                resultSet.close();

                return new Client(currentId, name, surname, age, phone, email);
            }

        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! "+e.getMessage());
        }
        return null;
    }

    @Override
    public void modifyClient(Client client, String newName) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("update client set name = ? where id = ?");)
        {
            statement.setString(1,newName);
            statement.setLong(2,client.getId());
            statement.execute();

        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! "+e.getMessage());
        }
    }

    @Override
    public List<Client> getClientsList() {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("select * from client order by 1");)
        {
            try (ResultSet resultSet = statement.executeQuery();)
            {
                List<Client> result = new ArrayList<>();
                while (resultSet.next())
                {
                    long currentId = resultSet.getLong(1);
                    String name = resultSet.getString(2);
                    String surname = resultSet.getString(3);
                    int age = resultSet.getInt(4);
                    String phone = resultSet.getString(5);
                    String email = resultSet.getString(6);

                    result.add(new Client(currentId, name, surname, age, phone, email));
                }
                return result;
            }

        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! "+e.getMessage());
        }

        return null;
    }

    @Override
    public void deleteClient(Client client) {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("delete from client where id = ?");)
        {
            statement.setLong(1,client.getId());
            statement.execute();

        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! "+e.getMessage());
        }
    }
}
