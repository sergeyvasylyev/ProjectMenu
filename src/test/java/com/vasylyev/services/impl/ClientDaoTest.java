package com.vasylyev.services.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.domain.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.beans.Statement;
import java.sql.*;

import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//import static org.testng.AssertJUnit.assertEquals;
//import org.powermock.api.mockito.PowerMockito;
//import static org.powermock.api.mockito.PowerMockito.mock;
//import static org.powermock.api.mockito.PowerMockito.spy;


@RunWith(MockitoJUnitRunner.class)
public class ClientDaoTest {

    @Mock
    private ClientDao clientDao;
    @Mock
    private Statement statement;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;


    @Before
    public void init(){

    }

    @Test
    public void createClientDAOTest() throws SQLException {

        long id = 1;
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0501234455";
        String email = "test@test.com";

        Client clientToSave = new Client.Builder(name, phone)
                .surname(surname)
                .age(age)
                .email(email)
                .buildClient();

        //PowerMockito.mockStatic(DriverManager.class);
        //when(DriverManager.getConnection(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(connection);

        //when(connection.createStatement()).thenReturn(statement);
        when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        when(preparedStatement.execute()).thenReturn(Boolean.TRUE);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(Boolean.TRUE);

        when(resultSet.getLong(1)).thenReturn(clientToSave.getId());
        when(resultSet.getString(2)).thenReturn(clientToSave.getName());
        when(resultSet.getString(3)).thenReturn(clientToSave.getSurname());
        when(resultSet.getInt(4)).thenReturn(clientToSave.getAge());
        when(resultSet.getString(5)).thenReturn(clientToSave.getPhone());
        when(resultSet.getString(6)).thenReturn(clientToSave.getEmail());

        clientDao.saveClient(clientToSave);
        Client clientToFind = clientDao.findClient(clientToSave.getId());

        assertEquals(clientToSave, clientToFind);

    }

    @After
    public void tearDown(){


    }
}
