package com.vasylyev.domain;

import java.util.List;

public class Order {

    private long id;
    private Client client;
    List<Product> products;

    public Order(Client client) {
        this.client = client;
    }

    public Order(Client client, List<Product> products) {
        this.client = client;
        this.products = products;
    }

    public Order(long id, Client client, List<Product> products) {
        this.id = id;
        this.client = client;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client.toStringShort() +
                ", products=" + products +
                '}';
    }
}
