package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static sun.nio.ch.IOStatus.EOF;

public class Computer {

    private static boolean checkInput(int input) {
        return input==1||input==2;
    }
    public static void main(String[] args){
        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Computer computer = new Computer();
        while(true) {
            computer.inputOutput(parkingBoy);
        }
    }

    public  void inputOutput(ParkingBoy parkingBoy){

            System.out.println("1. 停车\n" +
                    "2. 取车 \n" +
                    "请输入您要进行的操作：");
            Scanner scanner = new Scanner(System.in);
            Integer input = scanner.nextInt();
            if(checkInput(input)) {
                if(input==1){
                    inputPark(parkingBoy);
                }
                else{
                    inputUnpark(parkingBoy);
                }
            }
            else{
                System.out.print("非法指令，请查证后再输");
            }

    }

    public void inputUnpark(ParkingBoy parkingBoy) {
        System.out.println("请输入小票编号：");
        Scanner scanner = new Scanner(System.in);
        String receiptId = scanner.next();
        UUID carUUID = UUID.fromString(receiptId);
        try {
            Receipt receipt3 = new Receipt(carUUID);
            Car car2 = parkingBoy.unPark(receipt3);
            System.out.println("车已取出，您的车牌号是: "+car2.getId());
        }catch (NotTrueReceiptException e){
            System.out.println("非法小票，无法取出车，请查证后再输");
        }
    }

    public void inputPark(ParkingBoy parkingBoy) {
        Scanner scanner = new Scanner(System.in);
        if(!parkingBoy.allParkIsFull()) {
            System.out.println("请输入车牌号:");
            String carId = scanner.next();
            Car car = new Car(carId);
            Receipt receipt = parkingBoy.parking(car);
            System.out.println("停车成功，您的小票是：\n" + receipt.uuid);
        }else
            System.out.println("车已停满，请晚点再来");
    }
}
