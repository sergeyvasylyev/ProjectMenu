package com.vasylyev.config;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.dao.OrderDao;
import com.vasylyev.dao.ProductDao;
import com.vasylyev.dao.impl.ClientDBDao;
import com.vasylyev.dao.impl.OrderDBDao;
import com.vasylyev.dao.impl.ProductDBDao;
import com.vasylyev.services.ClientService;
import com.vasylyev.services.OrderService;
import com.vasylyev.services.ProductService;
import com.vasylyev.services.impl.ClientServiceImpl;
import com.vasylyev.services.impl.OrderServiceImpl;
import com.vasylyev.services.impl.ProductServiceImpl;
import com.vasylyev.validators.ValidationService;
import com.vasylyev.view.AdminMenu;
import com.vasylyev.view.ClientMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

@Configuration
@ComponentScan(basePackages = "com.vasylyev")
public class AppConfig {

    @Bean
    public BufferedReader bufferedReader() throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(System.in));
    }

/*
    ClientDao clientDao;
    ProductDao productDao;
    OrderDao orderDao;
    ValidationService validationService;

    @Bean
    public ClientDao getClientDao(){
        return new ClientDBDao();
    }
    @Bean
    public ProductDao getProductDao(){
        return new ProductDBDao();
    }
    @Bean
    public OrderDao getOrderDao(){
        return new OrderDBDao();
    }

    @Bean
    public ClientService getClientService(){
        return new ClientServiceImpl(clientDao, validationService);
    }

    @Bean
    public ProductService getProductService(){
        return new ProductServiceImpl(productDao);
    }

    @Bean
    public OrderService getOrderService(){
        return new OrderServiceImpl(clientDao, productDao, orderDao);
    }

*/
}
