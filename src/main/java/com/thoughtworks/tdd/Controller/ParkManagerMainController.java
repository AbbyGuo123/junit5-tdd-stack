package com.thoughtworks.tdd.Controller;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;

public class ParkManagerMainController implements BaseController{
    private final Request request;
    private final Response response;
    ParkingBoy parkingBoy ;

    public ParkManagerMainController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String proccess(){
        String forwardPath="";
        response.send("1.查看停车场详情\n" +
                "2.添加停车场\n" +
                "3.删除停车场");
        return forwardPath;
    }
}
