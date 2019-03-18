package com.vasylyev.services.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.dao.OrderDao;
import com.vasylyev.dao.ProductDao;
import com.vasylyev.domain.Client;
import com.vasylyev.domain.Order;
import com.vasylyev.domain.Product;
import com.vasylyev.services.OrderService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderDao orderDao;
    @Mock
    private ProductDao productDao;
    @Mock
    private ClientDao clientDao;

    private OrderService orderService;

    @Before
    public void init(){
        orderService = new OrderServiceImpl(clientDao, productDao, orderDao);
    }

    @Test
    public void createProductWithFullParametersTest() {
        //GIVEN
        Long clientId = 1L;
        String name = "test";
        String phone = "0501234455";
        Client testClient = new Client.Builder(clientId, name, phone).build();

        Long productId = 1L;
        String productName = "test";
        BigDecimal price = new BigDecimal(11.11);
        Product testProduct = new Product(productId, productName, price);
        List<Product> productList = Arrays.asList(testProduct);
        List<String> productNameList = Arrays.asList(productName);

        //WHEN
        when(clientDao.findClient(clientId)).thenReturn(testClient);
        when(productDao.findProduct(productName)).thenReturn(testProduct);
        orderService.createOrder(clientId, productNameList);

        //THEN
        verify(orderDao,times(1))
                .saveOrder(new Order(testClient, productList));
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
        Client newClient = new Client.Builder(name, phone)
                .surname(surname)
                .age(age)
                .email(email)
                .build();

        Order expectedOrder= new Order(newClient);
        when(orderDao.findOrder(id)).thenReturn(expectedOrder);

        //WHEN
        Order order = orderService.findOrder(id);

        //THEN
        Assert.assertEquals(expectedOrder, order);
    }

    @After
    public void tearDown(){
        orderService = null;
    }
}
