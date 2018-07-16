package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static sun.nio.ch.IOStatus.EOF;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Request request = new Request();
        Response response = new Response();
        String currentPage = "main";

        ParkingLot parkingLot = new ParkingLot(1);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        Controller controller = new Controller(request,response,parkingBoy);
        Router router = new Router(controller,currentPage);
        while (true) {
            String command = scanner.next();
            request.setCommand(command);
            router.handleCommand(request);
        }
    }
}
