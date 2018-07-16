package com.thoughtworks.tdd.Controller;


import com.thoughtworks.tdd.*;
import com.thoughtworks.tdd.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class ParkManagerController extends ParkController{

    private List<ParkingLot> parkingLotList;

    public ParkManagerController(Request request, Response response, ParkingBoy parkingBoy,List<ParkingLot> parkingLotList) {
        super(request,response,parkingBoy);
        this.parkingLotList = parkingLotList;
    }



    public void main2Page(){
        super.getResponse().send("1. 停车\n" + "2. 取车 \n" + "请输入您要进行的操作：");
    }



    public void mainPage(){
        super.getResponse().send("1.停车服务\n" +
                "2.停车场管理\n" +
                "请输入您要进入的页面：");
    }

    public void packManagerPage(){
        super.getResponse().send("1.查看停车场详情\n" +
                "2.添加停车场\n" +
                "3.删除停车场");
    }


    //删除停车场
    public void deleteParkingById() {
        String parkId = super.getRequest().getCommand();
        List<ParkingLot> list = parkingLotList.stream().filter(parkingLot -> parkingLot.getId().equals(parkId)).collect(Collectors.toList());
        if(list.size()>0) {
            ParkingLot parkingLot1 = list.get(0);
            if(parkingLot1.getHasCarSize()==0) {
                parkingLotList.remove(parkingLot1);
                deleteParkByIdSuccess();
            }
            else{
                deleteParkByIdFailPagehasCar();
            }
        }
        else{
            deleteParkByIdFailPageNotFound();
        }

    }

    public  void deleteParkByIdPage(){super.getResponse().send("请输入需要删除的被管理停车场ID:");}
    public void deleteParkByIdFailPageNotFound(){
        super.getResponse().send("停车场添加失败，原因：此停车场不存在！");
    }
    public void deleteParkByIdFailPagehasCar(){
        super.getResponse().send("此停车场中，依然停有汽车，无法删除！");
    }
    public void deleteParkByIdSuccess(){
        super.getResponse().send("停车场删除成功！");
    }

    //添加停车场
    public void addParkingLotByNameAndSize() {
        String input = super.getRequest().getCommand();
        String[] inputArray = input.split(",");
        int size = Integer.parseInt(inputArray[1]);
        ParkingLot parkingLot = new ParkingLot("003",inputArray[0],size,0);
        this.parkingLotList.add(parkingLot);
        addParkSuccess();
    }

    public void addParkByNameAndSizePage(){super.getResponse().send("请输入你套添加的停车场信息（格式为：名称，车位）：");}
    public void addParkSuccess(){super.getResponse().send("停车场添加成功！");}
    //查看停车场信息

    public void generateParkingDetails() {
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
        showParkingDetails(parkingDetails);
    }
    public void showParkingDetails(String parkingDetails){super.getResponse().send(parkingDetails);}



}
