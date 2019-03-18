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
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

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
    public void createProductWithFullParametersTest() {
        //GIVEN
        String name = "test";
        BigDecimal price = new BigDecimal(11.11);

        //WHEN
        productService.createProduct(name, price);

        //THEN
        verify(productDao,times(1))
                .saveProduct(new Product(name, price));
    }

    @Test
    public void findClientTest() {
        //GIVEN
        String name = "test";
        BigDecimal price = new BigDecimal(10);

        Product expectedProduct = new Product(name, price);
        when(productDao.findProduct(name)).thenReturn(expectedProduct);

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
