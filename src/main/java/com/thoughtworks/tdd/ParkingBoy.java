package com.thoughtworks.tdd;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> pakingLotList;

    public ParkingBoy(List<ParkingLot> pakingLotList) {
        this.pakingLotList = pakingLotList;
    }

    public Receipt parking(Car car) {
        int num =0;
        Receipt receipt = null;
        for(ParkingLot parkingLot:pakingLotList){
            try {
                receipt = parkingLot.park(car);
                break;
            }catch (ParkingLotFullException e){
                num++;
            }
        }
        if(receipt==null)
             throw new ParkingLotFullException();
        return receipt;
    }

    public Car unPark(Receipt receipt) {
        Car car = null;
        for(ParkingLot parkingLot:pakingLotList) {
            if(parkingLot.isTheCarInTheParkingLot(receipt)){
                car = parkingLot.unPark(receipt);
            }
        }
        return car;
    }
}
