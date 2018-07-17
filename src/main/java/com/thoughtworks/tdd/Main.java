package com.thoughtworks.tdd;

import com.thoughtworks.tdd.Controller.ParkManagerController;
import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.ParkingLot;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Request request = new Request();
        Response response = new Response();
        String currentPage = "main";

        ParkingLot parkingLot = new ParkingLot("001","西南停车场",28,8);
        ParkingLot parkingLot1 = new ParkingLot("002","西南停车场",28,0);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        parkingLotList.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        ParkManagerController parkManagerController = new ParkManagerController(request,response,parkingBoy,parkingLotList);
        ParkManagerRouter parkManagerRouter = new ParkManagerRouter(parkManagerController,currentPage);
        while (true) {
            String command = scanner.next();
            request.setCommand(command);
            parkManagerRouter.handleCommand(request);
        }
    }
}
