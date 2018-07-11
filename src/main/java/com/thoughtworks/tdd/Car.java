package com.thoughtworks.tdd;

public class Car {
    String id;

    public Car(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Ticket parking(Park park) {
        Ticket ticket = new Ticket("1",this,park);
        return ticket;
    }

    public Car exparking(Ticket ticket) {
        Park park = ticket.park;
        park.setRelaxNumber(park.relaxNumber-1);
        return ticket.car;
    }
}
