package com.vasylyev.services.impl;

import com.vasylyev.dao.ProductDao;
import com.vasylyev.domain.Product;
import com.vasylyev.services.ProductService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductDao productDao;

    private ProductService productService;

    @Before
    public void init(){
        productService = new ProductServiceImpl(productDao);
    }

    @Test
    public void findClientTest() {
        //GIVEN
        String name = "test";
        BigDecimal price = new BigDecimal(10);

        Product expectedProduct = new Product(name, price);
        Mockito.when(productDao.findProduct(name)).thenReturn(expectedProduct);

        //WHEN
        Product product = productService.findProduct(name);

        //THEN
        Assert.assertEquals(expectedProduct, product);
    }

    @After
    public void tearDown(){
        productService = null;
    }
}
