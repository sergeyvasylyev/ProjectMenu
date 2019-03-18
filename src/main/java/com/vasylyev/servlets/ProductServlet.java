package com.vasylyev.servlets;

import com.vasylyev.domain.Product;
import com.vasylyev.services.ProductService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

public class ProductServlet extends HttpServlet {

    private ProductService productService;

    public ProductServlet(ProductService productService) {
        this.productService = productService;
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
        List<Product> productList = productService.GetAllProducts();
        if (productList == null ){
            writer.println("<h1>Empty product list!</h1>");
        }else {
            writer.println("<h4>Product list</h4>");
            for (Product product : productList) {
                writer.println("<h5>" + product.toString() + "</h5>");
            }
        }
        writer.println("<a href=\"productMenu.html\"> Product menu</a><br>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if ("/modify".equals(pathInfo)){
            doPut(req, resp);
            return;
        }
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        productService.createProduct(name, new BigDecimal(price));
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        productService.deleteProduct(name);
        PrintWriter writer = resp.getWriter();
        writer.println("Product "+"\""+name+"\" deleted!");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String newName = req.getParameter("newName");
        productService.modifyProduct(name, newName);
        doGet(req, resp);
    }
}
