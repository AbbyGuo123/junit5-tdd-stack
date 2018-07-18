package com.thoughtworks.tdd.Controller;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;

public class GotoAddParkController implements BaseController{
    private final Request request;
    private final Response response;
    ParkingBoy parkingBoy;

    public GotoAddParkController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String proccess(){
        String forwardPath="";
        response.send("请输入你套添加的停车场信息（格式为：名称，车位）：");
        return forwardPath;
    }
}
