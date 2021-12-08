package com.example.demo.model;




import javax.persistence.*;
import java.util.List;


// POWSTAŁO TO Z WIDOKU KTÓRY DLA WYGODY WCZEŚNIEJ STWORZYŁEM
@Entity
@Table(name = "customerwithpaymentcount")
public class CustomerPaymentView {
    @Id
    @Column(name = "customer_id")
    private int id;
    @Basic
    @Column(name = "name")
    private String FirstName;
    @Basic
    @Column(name = "surname")
    private String SurName;
    @Basic
    @Column(name = "all_paid")
    private int paid;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getSurName() {
        return SurName;
    }

    public int getPayments() {
        return paid;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public void setPayments(int rented) {
        this.paid = rented;
    }
    public static class Response {
        public static class customer{
            public int getId() {
                return id;
            }
            public void setId(int id) {
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
            private int id;
            private String name;
            private String surname;
            public customer(int id, String name, String surname){
                this.id=id;
                this.name=name;
                this.surname=surname;
            }
        }

        public customer getCustomer() {
            return customer;
        }

        public void setCustomer(customer customer) {
            this.customer = customer;
        }


        private customer customer;

        public int getSpent() {
            return spent;
        }

        public void setSpent(int paym) {
            this.spent = paym;
        }

        public Response(int id, String name, String surname, int paym){
            this.customer= new customer(id, name, surname);
            this.spent=paym;
        }
        private int spent;
    }
    public Response getResponse(){
        return new Response(this.getId(),this.getFirstName(),this.getSurName(),this.getPayments());
    }

}
