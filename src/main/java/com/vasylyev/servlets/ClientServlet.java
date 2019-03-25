package com.vasylyev.servlets;

import com.vasylyev.domain.Client;
import com.vasylyev.services.ClientService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ClientServlet extends HttpServlet {

    private ClientService clientService;

    public ClientServlet(ClientService clientService) {
        this.clientService = clientService;
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
        List<Client> clientList = clientService.getAllClients();
        if (clientList == null ){
            writer.println("<h1>Empty client list!</h1>");
        }else {
            writer.println("<h4>Client list</h4>");
            for (Client client : clientList) {
                writer.println("<p>" + client.toString() + "</p>");
            }
        }
        writer.println("<a href=\"clientMenu.html\"> Client menu</a><br>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if ("/modify".equals(pathInfo)){
            doPut(req, resp);
            return;
        }
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String age = req.getParameter("age");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        clientService.createClient(name, surname, Integer.parseInt(age), phone, email);
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        clientService.deleteClient(Long.parseLong(id));
        PrintWriter writer = resp.getWriter();
        writer.println("Client "+"\""+id+"\" deleted!");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        clientService.modifyClient(Long.parseLong(id), name);
        doGet(req, resp);
    }
}
