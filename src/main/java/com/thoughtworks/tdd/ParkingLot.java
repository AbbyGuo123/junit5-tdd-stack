package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ParkingLot {
    private int size;
    private Map<Receipt, Car> map = new HashMap<>();

    public ParkingLot(int size) {
        this.size = size;
    }

    public Receipt park(Car car) {
        if (isFull())
            throw new ParkingLotFullException();
        UUID uuid = UUID.randomUUID();
        Receipt reciept = new Receipt(uuid);
        map.put(reciept, car);
        return reciept;
    }


    public Car unPark(Receipt receipt) {
        return map.remove(receipt);
    }

    public boolean isFull() {
        return this.size == map.size();
    }

    public boolean isTheCarInTheParkingLot(Receipt receipt) {
        return this.map.get(receipt)!= null;
    }
}
