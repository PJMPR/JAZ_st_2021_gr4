package com.example.demo.model;


import javax.persistence.*;


// POWSTAŁO TO Z WIDOKU KTÓRY DLA WYGODY WCZEŚNIEJ STWORZYŁEM
@Entity
@Table(name = "customerwithrentalcount")
public class CustomerRentalView {
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
    @Column(name = "all_rented")
    private int rented;

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

    public int getRented() {
        return rented;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public void setRented(int rented) {
        this.rented = rented;
    }
    public static class Response {
        public class customer{
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

        public int getWatched() {
            return watched;
        }

        public void setWatched(int watched) {
            this.watched = watched;
        }

        private customer customer;
        private int watched;
        public Response(int id, String name, String surname, int watched){
            this.customer=new customer(id,name,surname);
            this.watched=watched;
        }
    }
    public Response getResponse(){
        return new Response(this.getId(),this.getFirstName(),this.getSurName(),this.getRented());
    }

}
