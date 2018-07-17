package com.thoughtworks.tdd.Controller;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;

public class GotoUnParkController implements BaseController{
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public GotoUnParkController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }
    @Override
    public String proccess(){
        String forwardPath="";
        response.send("请输入小票编号：");
        return forwardPath;
    }
}
