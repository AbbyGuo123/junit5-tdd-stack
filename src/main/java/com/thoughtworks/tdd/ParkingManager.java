package com.thoughtworks.tdd;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingManager {
    List<ParkingLot> parkingLotList ;
    Response response ;

    public ParkingManager(List<ParkingLot> parkingLotList,Response response) {
        this.parkingLotList = parkingLotList;
        this.response = response;
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

    public void addParkingLotByNameAndSize(String name,int size) {
        ParkingLot parkingLot = new ParkingLot("003",name,size,0);
        this.parkingLotList.add(parkingLot);
        response.send("停车场添加成功！");
    }

    public void deleteParkingById(String s) {
        List<ParkingLot> list = parkingLotList.stream().filter(parkingLot -> parkingLot.getId().equals(s)).collect(Collectors.toList());
        if(list.size()>0) {
            ParkingLot parkingLot1 = list.get(0);
            if(parkingLot1.getHasCarSize()==0) {
                parkingLotList.remove(parkingLot1);
                response.send("停车场删除成功！");
            }
            else{
                response.send("此停车场中，依然停有汽车，无法删除！");
            }
        }
        else{
            response.send("停车场添加失败，原因：此停车场不存在！");
        }

    }
}
