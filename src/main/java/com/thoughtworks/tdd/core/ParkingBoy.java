package com.thoughtworks.tdd.core;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> pakingLotList;
    private int parkCount;

    public int getParkCount() {
        return parkCount;
    }

    public void setParkCount(int parkCount) {
        this.parkCount = parkCount;
    }

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

    public Boolean allParkIsFull() {
        boolean flag = true;
        for(ParkingLot parkingLot:this.pakingLotList){
            if(!parkingLot.isFull()){
                flag = false;
            }
        }
        return flag;
    }
}
