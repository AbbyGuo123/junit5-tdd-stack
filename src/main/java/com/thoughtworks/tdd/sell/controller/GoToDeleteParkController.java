package com.thoughtworks.tdd.sell.controller;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;

public class GoToDeleteParkController implements BaseController {
    private final Request request;
    private final Response response;
    ParkingBoy parkingBoy;

    public GoToDeleteParkController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String proccess(){
        String forwardPath ="";
        response.send("请输入需要删除的被管理停车场ID:");
        return forwardPath;
    }
}
