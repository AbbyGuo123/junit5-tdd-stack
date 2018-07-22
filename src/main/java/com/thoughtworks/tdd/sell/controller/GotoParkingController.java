package com.thoughtworks.tdd.sell.controller;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;

public class GotoParkingController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public GotoParkingController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }
    @Override
    public String proccess(){
        String forwardPath = "";
        if(!parkingBoy.allParkIsFull()) {
            response.send("请输入车牌号:");

        }else {
            response.send("车已停满，请晚点再来");
            forwardPath = "forward:main";
        }
        return forwardPath;
    }
}
