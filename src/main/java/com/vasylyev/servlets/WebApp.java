package com.vasylyev.servlets;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.dao.OrderDao;
import com.vasylyev.dao.ProductDao;
import com.vasylyev.dao.impl.ClientDBDao;
import com.vasylyev.dao.impl.ClientEMDao;
import com.vasylyev.dao.impl.OrderDBDao;
import com.vasylyev.dao.impl.ProductDBDao;
import com.vasylyev.services.ClientService;
import com.vasylyev.services.OrderService;
import com.vasylyev.services.ProductService;
import com.vasylyev.services.impl.ClientServiceImpl;
import com.vasylyev.services.impl.OrderServiceImpl;
import com.vasylyev.services.impl.ProductServiceImpl;
import com.vasylyev.servlets.filters.ClientFilter;
import com.vasylyev.validators.ValidationService;
import com.vasylyev.validators.impl.ValidationServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebApp implements ServletContextListener {

    ClientDao clientDao = new ClientEMDao();
    //ClientDao clientDao = new ClientDBDao();
    ProductDao productDao = new ProductDBDao();
    OrderDao orderDao = new OrderDBDao();
    ValidationService validationService = new ValidationServiceImpl();
    ClientService clientService = new ClientServiceImpl(clientDao, validationService);
    ProductService productService = new ProductServiceImpl(productDao);
    OrderService orderService = new OrderServiceImpl(clientDao, productDao, orderDao);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext
                .addServlet("ClientServlet", new ClientServlet(clientService))
                .addMapping("/clients/*");

        servletContext
                .addServlet("ProductServlet", new ProductServlet(productService))
                .addMapping("/products/*");
        servletContext
                .addServlet("OrderServlet", new OrderServlet(orderService))
                .addMapping("/orders/*");

        /*
        servletContext
                .addFilter("ClientFilter", new ClientFilter(validationService))
                .addMappingForUrlPatterns("");
                */
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
