package com.thoughtworks.tdd;

public class Ticket {
    String id;
    Car car;
    Park park;


    public Ticket(String id, Car car, Park park) {
        this.id = id;
        this.car = car;
        this.park = park;
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

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }
}
