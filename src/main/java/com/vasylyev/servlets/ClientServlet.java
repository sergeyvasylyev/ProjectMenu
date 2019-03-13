package com.vasylyev.servlets;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.dao.impl.ClientDBDao;
import com.vasylyev.domain.Client;
import com.vasylyev.services.ClientService;
import com.vasylyev.services.impl.ClientServiceImpl;
import com.vasylyev.validators.ValidationService;
import com.vasylyev.validators.impl.ValidationServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/clients")
public class ClientServlet extends HttpServlet {
    ClientDao clientDao = new ClientDBDao();
    ValidationService validationService = new ValidationServiceImpl();
    private ClientService clientService = new ClientServiceImpl(clientDao, validationService);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //clientService.getAllClients();
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        List<Client> clientList = clientService.getAllClients();
        if (clientList == null ){
            writer.println("<h1>Empty client list!</h1>");
        }else {
            for (Client client : clientList) {
                writer.println("<h1>" + client.toString() + "</h1>");
            }
        }
    }
}
