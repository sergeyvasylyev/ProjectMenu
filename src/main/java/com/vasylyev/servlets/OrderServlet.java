package com.vasylyev.servlets;

import com.vasylyev.domain.Order;
import com.vasylyev.services.OrderService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class OrderServlet extends HttpServlet {

    OrderService orderService;
    public OrderServlet(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if ("/delete".equals(pathInfo)){
            doDelete(req, resp);
            return;
        }
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        List<Order> orderList = orderService.GetAllOrders();
        if (orderList == null ){
            writer.println("<h1>Empty order list!</h1>");
        }else {
            writer.println("<h4>Order list</h4>");
            for (Order order : orderList) {
                writer.println("<h5>" + order.toString() + "</h5>");
            }
        }
        writer.println("<a href=\"orderMenu.html\"> Order menu</a><br>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String clientId = req.getParameter("clientId");
        String prName1 = req.getParameter("prName1");
        String prName2 = req.getParameter("prName2");
        String prName3 = req.getParameter("prName3");
        List<String> productList = Arrays.asList(prName1, prName2, prName3);
        orderService.createOrder(Long.parseLong(clientId), productList);
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        orderService.deleteOrder(Long.parseLong(id));
        PrintWriter writer = resp.getWriter();
        writer.println("Order "+"\""+id+"\" deleted!");
    }
}
