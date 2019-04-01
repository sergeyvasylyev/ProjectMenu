package com.vasylyev.servlets;

import com.vasylyev.services.ClientService;
import com.vasylyev.services.OrderService;
import com.vasylyev.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebApp implements ServletContextListener {

    @Autowired
    ClientService clientService;
    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;

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
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
