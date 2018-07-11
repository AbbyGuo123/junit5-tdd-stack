package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int size;
    private Map<Receipt, Car> map = new HashMap<>();

    public ParkingLot(int size) {
        this.size = size;
    }

    public Receipt park(Car car) {
        if (isFull())
            throw new ParkingLotFullException();
        Receipt reciept = new Receipt();
        map.put(reciept, car);
        return reciept;
    }


    public Car unPark(Receipt receipt) {
        return map.remove(receipt);
    }

    public boolean isFull() {
        return this.size == map.size();
    }
}
