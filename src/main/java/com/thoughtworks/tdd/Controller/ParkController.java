package com.thoughtworks.tdd.Controller;


import com.thoughtworks.tdd.*;
import com.thoughtworks.tdd.model.*;

public class ParkController {

    private Response response;

    private Request request;

    ParkingBoy parkingBoy;

    public ParkController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
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

    public void mainPage(){
        response.send("1. 停车\n" + "2. 取车 \n" + "请输入您要进行的操作：");
    }

    public void Not(){
        response.send("非法指令，请查证后再输");
    }
}
