package com.vasylyev.dao.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.domain.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {

   private List<Client> clientList = new ArrayList<>();

   @Override
   public void saveClient(Client client) {
       client.setId(getMaxId() + 1);
       System.out.println("Save client: "+client.getName());
       clientList.add(client);
   }

   @Override
   public Client findClient(String clientName){
        for (int i = 0; i < clientList.size(); i++) {
            Client foundClient = clientList.get(i);
            if (clientName.equals(foundClient.getName())){
                return foundClient;
            };
        }
        return null;
   }

   @Override
   public void modifyClient(Client client, String newName){
        client.setName(newName);
        System.out.println("Save client: "+client.getName());
   }

   @Override
   public List<Client> getClientsList(){
       return clientList;
    }

   @Override
   public void deleteClient(Client client){
        for (int i = 0; i < clientList.size(); i++) {
            Client foundClient = clientList.get(i);
            if (client.equals(foundClient)){
                System.out.println("Remove client: "+client.getName());
                clientList.remove(i);
                break;
            };
        }
   }

   private long getMaxId(){
        long maxId = 0;
        for (int i = 0; i < clientList.size(); i++) {
            Client foundClient = clientList.get(i);
            if (foundClient.getId() > maxId){
                maxId = foundClient.getId();
            }
        }
        return maxId;
   }
}
