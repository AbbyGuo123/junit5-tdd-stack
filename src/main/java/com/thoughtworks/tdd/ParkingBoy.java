package com.thoughtworks.tdd;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> pakingLotList;

    public ParkingBoy(List<ParkingLot> pakingLotList) {
        this.pakingLotList = pakingLotList;
    }

    public void parking(Car car) {
        int num = 0;
        for(ParkingLot parkingLot:pakingLotList){
            try {
                parkingLot.park(car);
            }catch (ParkingLotFullException exception){
                num++;
            }

        }
        if(num == pakingLotList.size())
            throw new ParkingLotFullException();
    }
}
