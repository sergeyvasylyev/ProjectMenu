package com.vasylyev.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private long id;

    @Column(name = "NAME")
    private String name;
    private String surname;
    private int age;
    private String phone;
    private String email;

    public Client() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String toStringShort() {
        return name + "{" +
                "id:" + id +
                ", ph:" + phone +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                age == client.age &&
                Objects.equals(name, client.name) &&
                Objects.equals(surname, client.surname) &&
                Objects.equals(email, client.email) &&
                Objects.equals(phone, client.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age, email, phone);
    }

    public static class Builder {
        private long id;
        private String name;
        private String phone;
        private String surname;
        private int age;
        private String email;

        public Builder(long id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }

        public Builder(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }

        public Builder id(long vId) {
            id = vId;
            return this;
        }

        public Builder name(String vName) {
            name = vName;
            return this;
        }

        public Builder phone(String vPhone) {
            phone = vPhone;
            return this;
        }

        public Builder surname(String vSurname) {
            surname = vSurname;
            return this;
        }

        public Builder age(int vAge) {
            age = vAge;
            return this;
        }

        public Builder email(String vEmail) {
            email = vEmail;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }

    private Client(Builder builder) {
        id = builder.id;
        name = builder.name;
        surname = builder.surname;
        age = builder.age;
        email = builder.email;
        phone = builder.phone;
    }
}
