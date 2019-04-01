package com.vasylyev.services.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.domain.Client;
import com.vasylyev.services.ClientService;
import com.vasylyev.validators.ValidationService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    @Mock
    private ClientDao clientDao;
    @Mock
    private ValidationService validationService;

    private ClientService clientService;

    @Before
    public void init(){
        clientService = new ClientServiceImpl();
    }

    @Test
    public void createClientWithFullParametersTest() {
        //GIVEN
        String name = "test";
        String surname = "test";
        int age = 10;
        String phone = "0501234455";
        String email = "test@test.com";

        //WHEN
        clientService.createClient(name, surname, age, phone, email);

        //THEN
        verify(clientDao,times(1))
                .saveClient(new Client.Builder(name, phone)
                        .surname(surname)
                        .age(age)
                        .email(email)
                        .build());
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

        Client expectedClient = new Client.Builder(name, phone)
                .surname(surname)
                .age(age)
                .email(email)
                .build();
        when(clientDao.findClient(id)).thenReturn(expectedClient);

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
        when(clientDao.findClient(id)).thenReturn(null);

        //WHEN
        Client client = clientService.findClient(id);

        //THEN
        verify(clientDao).findClient(id);
        Mockito.verifyNoMoreInteractions(clientDao);
        Assert.assertNull( client);
    }

    @After
    public void tearDown(){
        clientService = null;
    }

}