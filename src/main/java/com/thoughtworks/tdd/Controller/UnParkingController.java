package com.thoughtworks.tdd.Controller;

import com.thoughtworks.tdd.core.Car;
import com.thoughtworks.tdd.core.NotTrueReceiptException;
import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.Receipt;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;

public class UnParkingController implements BaseController{
    private final Request request;
    private final Response response;
    private ParkingBoy parkingBoy;

    public UnParkingController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }
    @Override
    public String proccess(){
        String forwardPath = "";
        String receiptId = request.getCommand();
        try {
            Receipt receipt3 = new Receipt(receiptId);
            Car car2 = parkingBoy.unPark(receipt3);
            response.send("车已取出，您的车牌号是: "+car2.getId());

        }catch (NotTrueReceiptException e){
            response.send("非法小票，无法取出车，请查证后再输");
        }
        forwardPath = "forward:main";
        return forwardPath;
    }

}
