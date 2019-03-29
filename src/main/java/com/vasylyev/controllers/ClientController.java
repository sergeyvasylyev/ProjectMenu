package com.vasylyev.controllers;

import com.vasylyev.domain.Client;
import com.vasylyev.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClientController {

    private ClientService clientService;

    @GetMapping("/clients1")
    public String showClients(ModelMap modelMap){

        String clientListMessage = "";
        List<Client> clientList = clientService.getAllClients();
        for (Client client : clientList) {
            clientListMessage += "<p>" + client.toString() + "</p>";
        }
        clientListMessage += "<a href=\"clientMenu.html\"> Client menu</a><br>";
        modelMap.put("message",clientListMessage);
        return "clients";
    }

}
