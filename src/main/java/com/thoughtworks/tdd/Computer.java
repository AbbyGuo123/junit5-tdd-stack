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

    public  void inputOutput(ParkingBoy parkingBoy){

            System.out.print("1. 停车\n" +
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
        Scanner scanner = new Scanner(System.in);
        String receiptId = scanner.next();
        UUID carUUID = UUID.fromString(receiptId);
        try {
            Receipt receipt3 = new Receipt(carUUID);
            Car car2 = parkingBoy.unPark(receipt3);
            System.out.print("车已取出，您的车牌号是: "+car2.getId()+"\n");
        }catch (NotTrueReceiptException e){
            System.out.print("非法小票，无法取出车，请查证后再输\n");
        }
    }

    public void inputPark(ParkingBoy parkingBoy) {
        Scanner scanner = new Scanner(System.in);
        if(!parkingBoy.allParkIsFull()) {
            String carId = scanner.next();
            Car car = new Car(carId);
            Receipt receipt = parkingBoy.parking(car);
            System.out.print("停车成功，您的小票是：\n" + receipt.uuid);
        }else
            System.out.print("车已停满，请晚点再来");
    }
}
