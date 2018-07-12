package com.thoughtworks.tdd;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> pakingLotList;

    public ParkingBoy(List<ParkingLot> pakingLotList) {
        this.pakingLotList = pakingLotList;
    }

    public Receipt parking(Car car) {
        int num = 0;
        Receipt receipt = null;
        for(ParkingLot parkingLot:pakingLotList){
            try {
                receipt = parkingLot.park(car);

            }catch (ParkingLotFullException exception){
                num++;
            }

        }
        if(num == pakingLotList.size())
            throw new ParkingLotFullException();
        return receipt;
    }
}
