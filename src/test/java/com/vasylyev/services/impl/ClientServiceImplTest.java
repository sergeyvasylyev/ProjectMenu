package com.vasylyev.services.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.dao.impl.ClientDBDao;
import com.vasylyev.domain.Client;
import com.vasylyev.services.ClientService;
import com.vasylyev.validators.ValidationService;
import com.vasylyev.validators.impl.ValidationServiceImpl;
import org.junit.*;
import org.junit.internal.builders.JUnit4Builder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

//@RunWith(JUnit4.class)
@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    //private ClientDao clientDao = Mockito.mock(ClientDao.class);
    //private ValidationService validationService = Mockito.mock(ValidationService.class);

    @Mock
    private ClientDao clientDao;
    @Mock
    private ValidationService validationService;

    private ClientService clientService;


    @Before
    public void init(){
        clientService = new ClientServiceImpl(clientDao, validationService);
    }

    @Test
    @Ignore
    public void createClientWithFullParamenertsTest() {
        //GIVEN
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0501234455";
        String email = "test@test.com";

        //WHEN
        clientService.createClient(name, surname, age, phone, email);

        //THEN

    }

    @Test
    public void findClientTest() {
        //GIVEN
        long id = 1;
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0501234455";
        String email = "test@test.com";

        Client expectedClient = new Client(name, surname, age, phone, email);
        Mockito.when(clientDao.findClient(id)).thenReturn(expectedClient);

        //WHEN
        Client client = clientService.findClient(id);

        //THEN
        //Mockito.verify(clientDao).findClient((long) 0);
        Assert.assertEquals(expectedClient, client);
    }

    @Test
    //@Ignore
    public void findClientWithNegateIdTest() {
        //GIVEN
        long id = -1;
        Mockito.when(clientDao.findClient(id)).thenReturn(null);

        //WHEN
        Client client = clientService.findClient(id);

        //THEN
        Mockito.verify(clientDao).findClient(id);
        Mockito.verifyNoMoreInteractions(clientDao);
        Assert.assertNull( client);

    }

    @After
    public void tearDown(){
        clientService = null;
    }

}