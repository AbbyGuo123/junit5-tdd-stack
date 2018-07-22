package com.thoughtworks.tdd;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.ParkingLot;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;
import com.thoughtworks.tdd.sell.controller.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Request request = new Request();
        String initRootPath = "main";
        Router router = initRouter(initRootPath, request);
        router.launch();
        while (true) {
            String command = scanner.next();
            request.setCommand(command);
            router.processRequest(request);
        }

    }
    private static Router initRouter(String currentPage, Request request) {
        ParkingLot parkingLot = new ParkingLot("001","西南停车场",28,8);
        ParkingLot parkingLot1 = new ParkingLot("002","西南停车场",28,0);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        parkingLotList.add(parkingLot1);
        ParkingBoy boy = new ParkingBoy(parkingLotList);
        Response response = new Response();

        Router router = new Router(currentPage);
        router.registerRoute("main", new MainController(request, response, boy));
        router.registerRoute("main/1", new ParkMainController(request, response, boy));
        router.registerRoute("main/1/1", new GotoParkingController(request, response, boy));
        router.registerRoute("main/1/2", new GotoUnParkController(request, response, boy));
        router.registerRoute("main/1/1/*", new ParkingController(request, response, boy));
        router.registerRoute("main/1/2/*", new UnParkingController(request, response, boy));

        router.registerRoute("main/2", new ParkManagerMainController(request, response, boy));
        router.registerRoute("main/2/1", new ShowParkDetailsController(request, response, boy));
        router.registerRoute("main/2/2", new GotoAddParkController(request, response, boy));
        router.registerRoute("main/2/3", new GoToDeleteParkController(request, response, boy));
        router.registerRoute("main/2/2/*", new AddParkController(request, response, boy));
        router.registerRoute("main/2/3/*", new DeleteParkController(request, response, boy));

        return router;
    }
}
