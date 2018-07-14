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
        if(isTheCarInTheParkingLot(receipt)) {
            return map.remove(receipt);
        }
        else throw new NotTrueReceiptException();

    }

    public boolean isFull() {
        return this.size == map.size();
    }

    public boolean isTheCarInTheParkingLot(Receipt receipt) {
        return this.map.get(receipt)!= null;
    }
}
