package com.vasylyev.dao.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.domain.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.vasylyev.dao.impl.CommonDBDao.*;

public class ClientDBDao implements ClientDao {


    public ClientDBDao() {

        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            statement.execute(ClientSQLConstructor);

        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }
    }

    @Override
    public void saveClient(Client client) {
        setStatement(ClientSQLInsert, client, "");
    }

    @Override
    public Client findClient(Long id) {
        return findClientCommon(ClientSQLFindId, id);
    }

    @Override
    public Client findClient(String phoneNumber) {
        return findClientCommon(ClientSQLFindPhone, phoneNumber);
    }

    @Override
    public void modifyClient(Client client, String newName) {
        setStatement(ClientSQLUpdate, client, newName);
    }

    @Override
    public void deleteClient(Client client) {
        setStatement(ClientSQLDelete, client, "");
    }

    @Override
    public List<Client> getClientsList() {
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(ClientSQLGetList);) {
            try (ResultSet resultSet = statement.executeQuery();) {
                List<Client> result = new ArrayList<>();
                while (resultSet.next()) {
                    result.add(getClientRomRS(resultSet));
                }
                return result;
            }
        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }
        return null;
    }

    private void setStatement(String sqlSt, Client client, String newName){
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlSt);) {
            switch (sqlSt) {
                case ClientSQLUpdate:
                    statement.setString(1, newName);
                    statement.setLong(2, client.getId());
                    break;
                case ClientSQLDelete:
                    statement.setLong(1, client.getId());
                    break;
                case ClientSQLInsert:
                    statement.setString(1, client.getName());
                    statement.setString(2, client.getSurname());
                    statement.setInt(3, client.getAge());
                    statement.setString(4, client.getPhone());
                    statement.setString(5, client.getEmail());
                    break;
            }
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }
    }

    private <T> Client findClientCommon(String sqlSt, T param){
        try (Connection connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sqlSt);) {
            switch (sqlSt){
                case ClientSQLFindPhone:
                    statement.setString(1, (String)param);
                    break;
                case ClientSQLFindId:
                    statement.setLong(1, (Long) param);
                    break;
            }
            try (ResultSet resultSet = statement.executeQuery();) {
                if (resultSet.next()) {
                    return getClientRomRS(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error with SQL Connection! " + e.getMessage());
        }
        return null;
    }

    private Client getClientRomRS(ResultSet resultSet) throws SQLException{
        long currentId = resultSet.getLong(1);
        String name = resultSet.getString(2);
        String surname = resultSet.getString(3);
        int age = resultSet.getInt(4);
        String phone = resultSet.getString(5);
        String email = resultSet.getString(6);

        return new Client.Builder(currentId, name, phone)
                .surname(surname)
                .age(age)
                .email(email)
                .build();
    }
}
