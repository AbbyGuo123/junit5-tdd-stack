package com.thoughtworks.tdd;

public class Person {
    String id;
    Car car;
    Ticket ticket;

    public Person(String id, Car car) {
        this.id = id;
        this.car = car;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }



}
