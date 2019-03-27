package com.vasylyev.dao.impl;

import com.vasylyev.dao.ClientDao;
import com.vasylyev.domain.Client;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Component
@Primary
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
        entityManager.getTransaction().begin();
        Client client = entityManager.find(Client.class, id);
        entityManager.getTransaction().commit();
        return client;
    }

    @Override
    public Client findClient(String phoneNumber) {
        entityManager.getTransaction().begin();
        String hql = "from Client where phone = :phone";
        Client client = entityManager.createQuery(hql, Client.class)
                .setParameter("phone", phoneNumber)
                .getResultList()
                .stream().findFirst()
                .orElse(null);
        entityManager.getTransaction().commit();
        return client;
    }

    @Override
    public void modifyClient(Client client, String newName) {
        entityManager.getTransaction().begin();
        client.setName(newName);
        entityManager.persist(client);
        entityManager.getTransaction().commit();
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
        entityManager.getTransaction().begin();
        entityManager.remove(client);
        entityManager.getTransaction().commit();
    }
}
