package com.thoughtworks.tdd.Controller;


import com.thoughtworks.tdd.*;
import com.thoughtworks.tdd.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class ParkManagerController {

    private Response response;

    private Request request;

    private ParkingBoy parkingBoy;

    private List<ParkingLot> parkingLotList;

    public ParkManagerController(Request request, Response response, ParkingBoy parkingBoy,List<ParkingLot> parkingLotList) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
        this.parkingLotList = parkingLotList;
    }


    public String parkpage() {
        if(!parkingBoy.allParkIsFull()) {
            response.send("请输入车牌号:");
            return "park";
        }else {
            response.send("车已停满，请晚点再来");
            return "isFull";
        }
    }

    public void park() {
        String carId = request.getCommand();
        Car car = new Car(carId);
        Receipt receipt = parkingBoy.parking(car);
        parkSuccess(receipt);
    }

    public void unpark(){
        String receiptId = request.getCommand();
        try {
            Receipt receipt3 = new Receipt(receiptId);
            Car car2 = parkingBoy.unPark(receipt3);
            unparkSuccessPage(car2.getId());
        }catch (NotTrueReceiptException e){
            unparkFailPage();
        }
    }


    public void parkSuccess(Receipt receipt) {
        response.send("停车成功，您的小票是：\n" + receipt.getUuid());
    }

    public void unparkPage(){
        response.send("请输入小票编号：");
    }

    public void unparkSuccessPage(String carID){
        response.send("车已取出，您的车牌号是: "+carID);
    }

    public void unparkFailPage(){
        response.send("非法小票，无法取出车，请查证后再输");
    }

    public void main2Page(){
        response.send("1. 停车\n" + "2. 取车 \n" + "请输入您要进行的操作：");
    }

    public void Not(){
        response.send("非法指令，请查证后再输");
    }

    public void mainPage(){
        response.send("1.停车服务\n" +
                "2.停车场管理\n" +
                "请输入您要进入的页面：");
    }

    public void packManagerPage(){
        response.send("1.查看停车场详情\n" +
                "2.添加停车场\n" +
                "3.删除停车场");
    }


    //删除停车场
    public void deleteParkingById() {
        String parkId = request.getCommand();
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

    public  void deleteParkByIdPage(){response.send("请输入需要删除的被管理停车场ID:");}
    public void deleteParkByIdFailPageNotFound(){
        response.send("停车场添加失败，原因：此停车场不存在！");
    }
    public void deleteParkByIdFailPagehasCar(){
        response.send("此停车场中，依然停有汽车，无法删除！");
    }
    public void deleteParkByIdSuccess(){
        response.send("停车场删除成功！");
    }

    //添加停车场
    public void addParkingLotByNameAndSize() {
        String input = request.getCommand();
        String[] inputArray = input.split(",");
        int size = Integer.parseInt(inputArray[1]);
        ParkingLot parkingLot = new ParkingLot("003",inputArray[0],size,0);
        this.parkingLotList.add(parkingLot);
        addParkSuccess();
    }

    public void addParkByNameAndSizePage(){response.send("请输入你套添加的停车场信息（格式为：名称，车位）：");}
    public void addParkSuccess(){response.send("停车场添加成功！");}
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
    public void showParkingDetails(String parkingDetails){response.send(parkingDetails);}



}
