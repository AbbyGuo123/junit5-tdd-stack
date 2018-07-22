package com.thoughtworks.tdd.sell.controller;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.ParkingLot;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;

import java.util.List;
import java.util.stream.Collectors;

public class DeleteParkController implements BaseController {
    private final Request request;
    private final Response response;
    ParkingBoy parkingBoy;

    public DeleteParkController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String proccess(){
        String forwardPath="forward:main";
        String parkId = request.getCommand();
        List<ParkingLot> list = parkingBoy.getPakingLotList().stream().filter(parkingLot -> parkingLot.getId().equals(parkId)).collect(Collectors.toList());
        if(list.size()>0) {
            ParkingLot parkingLot1 = list.get(0);
            if(parkingLot1.getHasCarSize()==0) {
                parkingBoy.getPakingLotList().remove(parkingLot1);
                response.send("停车场删除成功！");

            }
            else{
                response.send("此停车场中，依然停有汽车，无法删除！");
            }
        }
        else{
            response.send("停车场添加失败，原因：此停车场不存在！");
        }
        return forwardPath;
    }
}
