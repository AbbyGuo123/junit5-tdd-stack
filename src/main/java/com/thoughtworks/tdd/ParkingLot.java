package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    int size;
    Map<Receipt,Car> map = new HashMap<>();
    public ParkingLot(int size) {
        this.size = size;
    }
    public Receipt park(Car car){
        Receipt reciept = new Receipt();
        if(this.size>0){
            map.put(reciept,car);
        }
        else{
            throw new ParkingLotFullException();
        }
        return reciept;
    }


    public Car unPark(Receipt receipt) {
        return map.get(receipt);
    }
}
