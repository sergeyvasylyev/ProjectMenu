package com.vasylyev.services.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.dao.OrderDao;
import com.vasylyev.dao.ProductDao;
import com.vasylyev.domain.Client;
import com.vasylyev.domain.Order;
import com.vasylyev.domain.Product;
import com.vasylyev.services.ClientService;
import com.vasylyev.services.OrderService;
import com.vasylyev.validators.ValidationService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderDao orderDao;
    @Mock
    private ProductDao productDao;
    @Mock
    private ClientDao clientDao;
    @Mock
    private ValidationService validationService;

    private OrderService orderService;
    private ClientService clientService;

    @Before
    public void init(){
        orderService = new OrderServiceImpl(clientDao, productDao, orderDao);
        clientService = new ClientServiceImpl(clientDao, validationService);
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

        //WHEN
        Client newClient = new Client(name, surname, age, phone, email);

        Order expectedOrder= new Order(newClient);
        Mockito.when(orderDao.findOrder(id)).thenReturn(expectedOrder);

        //WHEN
        Order order = orderService.findOrder(id);

        //THEN
        Assert.assertEquals(expectedOrder, order);
    }


    @After
    public void tearDown(){
        orderService = null;
        clientService = null;
    }
}
