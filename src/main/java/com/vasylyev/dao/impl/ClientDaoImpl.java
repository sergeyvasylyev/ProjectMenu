package com.vasylyev.dao.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.domain.Client;

public class ClientDaoImpl implements ClientDao {

   public void saveClient(Client client){
       System.out.println("Save client "+client.getName());
    }
}
