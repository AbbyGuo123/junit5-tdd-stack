package com.thoughtworks.tdd.sell.controller;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;

public class MainController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public MainController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String proccess(){
        String forwardPath ="";
        response.send("1.停车服务\n" +
                "2.停车场管理\n" +
                "请输入您要进入的页面：");
        return forwardPath;
    }
}
