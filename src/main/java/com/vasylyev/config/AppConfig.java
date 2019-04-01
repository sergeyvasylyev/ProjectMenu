package com.vasylyev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

@Configuration
@ComponentScan(basePackages = "com.vasylyev")
public class AppConfig {
    @Bean
    public BufferedReader bufferedReader() throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Bean
    public EntityManager entityManager()
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
        return factory.createEntityManager();
    }
}
