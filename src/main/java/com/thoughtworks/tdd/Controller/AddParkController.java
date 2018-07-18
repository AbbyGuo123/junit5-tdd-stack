package com.thoughtworks.tdd.Controller;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.ParkingLot;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;

public class AddParkController implements BaseController{
    private final Request request;
    private final Response response;
    ParkingBoy parkingBoy;

    public AddParkController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String proccess(){
        String forwardPath = "forward:main";
        String input = request.getCommand();
        String[] inputArray = input.split(",");
        int size = Integer.parseInt(inputArray[1]);
        ParkingLot parkingLot = new ParkingLot("003",inputArray[0],size,0);
        parkingBoy.getPakingLotList().add(parkingLot);
        response.send("停车场添加成功！");
        return forwardPath;
    }
}
