package com.thoughtworks.tdd.sell.controller;

import com.thoughtworks.tdd.core.Car;
import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.Receipt;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;

public class ParkingController implements BaseController {
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public ParkingController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }
    @Override
    public String proccess(){
        String forwardPath="";
        String carId = request.getCommand();
        Car car = new Car(carId);
        Receipt receipt = parkingBoy.parking(car);
        response.send("停车成功，您的小票是：\n" + receipt.getUuid());
        forwardPath = "forward:main";
        return forwardPath;
    }
}
