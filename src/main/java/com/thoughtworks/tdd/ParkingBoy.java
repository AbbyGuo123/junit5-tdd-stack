package com.thoughtworks.tdd;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> pakingLotList;

    public ParkingBoy(List<ParkingLot> pakingLotList) {
        this.pakingLotList = pakingLotList;
    }

    public Receipt parking(Car car) {
        Receipt receipt = null;
        for(ParkingLot parkingLot:pakingLotList){
            if(!parkingLot.isFull()){
                receipt = parkingLot.park(car);
                break;
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
        if(car==null) throw new NotTrueReceiptException();
        return car;
    }
}
