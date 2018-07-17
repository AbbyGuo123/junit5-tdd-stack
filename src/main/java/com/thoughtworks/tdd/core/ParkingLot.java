package com.thoughtworks.tdd.core;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    private String id;
    private String name;
    private int size;
    private int hasCarSize;
    private Map<String, Car> map = new HashMap<>();

    public ParkingLot(int size) {
        this.size = size;
    }

    public ParkingLot(String id, String name, int size, int hasCarSize) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.hasCarSize = hasCarSize;
    }

    public Receipt park(Car car) {
        if (isFull())
            throw new ParkingLotFullException();
        UUID uuid = UUID.randomUUID();
        Receipt reciept = new Receipt(uuid.toString());
        map.put(reciept.getUuid(), car);
        return reciept;
    }


    public Car unPark(Receipt receipt) {
        return map.remove(receipt.getUuid());
    }

    public boolean isFull() {
        return this.size == map.size();
    }

    public boolean isTheCarInTheParkingLot(Receipt receipt) {
        return this.map.get(receipt.getUuid())!= null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getHasCarSize() {
        return hasCarSize;
    }

    public void setHasCarSize(int hasCarSize) {
        this.hasCarSize = hasCarSize;
    }
}
