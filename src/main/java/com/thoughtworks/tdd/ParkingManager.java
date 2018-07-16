package com.thoughtworks.tdd;

import java.util.List;

public class ParkingManager {
    List<ParkingLot> parkingLotList ;

    public ParkingManager(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public String generateParkingDetails() {
        int allSize = 0;
        int allhasCarSize = 0;

        String parkingDetails = "|停车场ID|名称|车位|已停车辆|剩余车位|\n" +
                "======================================\n" ;
        for(ParkingLot parkingLot:parkingLotList){
            int relaxSize = parkingLot.getSize()-parkingLot.getHasCarSize();
            parkingDetails += "|"+parkingLot.getId()+"|"+parkingLot.getName()+"|"+parkingLot.getSize()+"(个)|"+parkingLot.getHasCarSize()+"(辆)|"+relaxSize+"(个)|\n" ;
            allSize+=parkingLot.getSize();
            allhasCarSize+=parkingLot.getHasCarSize();
        }
        int allRelaxSize = allSize-allhasCarSize;
        parkingDetails +="\n" +
                "总车位："+allSize+"(个)\n" +
                "停车总量："+allhasCarSize+"（辆）\n" +
                "总剩余车位："+allRelaxSize+"（个）";
        return parkingDetails;
    }
}
