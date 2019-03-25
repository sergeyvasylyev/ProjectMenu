package com.vasylyev.dao.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.domain.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ClientEMDao implements ClientDao {

    private EntityManager entityManager;

    public ClientEMDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public void saveClient(Client client) {
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
    }

    @Override
    public Client findClient(Long id) {
        return null;
    }

    @Override
    public Client findClient(String phoneNumber) {
        return null;
    }

    @Override
    public void modifyClient(Client client, String newName) {

    }

    @Override
    public List<Client> getClientsList() {
        entityManager.getTransaction().begin();
        List<Client> resultList = entityManager.createQuery("from Client order by id", Client.class).getResultList();
        entityManager.getTransaction().commit();
        return resultList;
    }

    @Override
    public void deleteClient(Client client) {

    }
}
