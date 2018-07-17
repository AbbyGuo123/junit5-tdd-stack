package com.thoughtworks.tdd.Controller;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;

public class MainController implements BaseController{
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
        String forwardPath = "";
        response.send("1. 停车\n" + "2. 取车 \n" + "请输入您要进行的操作：");
        return forwardPath;
    }
}
