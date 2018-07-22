package com.thoughtworks.tdd.sell.controller;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.ParkingLot;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;

public class ShowParkDetailsController implements BaseController {
    private final Request request;
    private final Response response;
    ParkingBoy parkingBoy;

    public ShowParkDetailsController(Request request, Response response, ParkingBoy parkingBoy) {
        this.request = request;
        this.response = response;
        this.parkingBoy = parkingBoy;
    }

    @Override
    public String proccess(){
        String forwardPath = "forward:main";
        int allSize = 0;
        int allhasCarSize = 0;

        String parkingDetails = "|停车场ID|名称|车位|已停车辆|剩余车位|\n" +
                "======================================\n" ;
        for(ParkingLot parkingLot:parkingBoy.getPakingLotList()){
            int relaxSize = parkingLot.getSize()-parkingLot.getHasCarSize();
            parkingDetails += "|"+parkingLot.getId()+"|"+parkingLot.getName()+"|"+parkingLot.getSize()+"(个)|"+parkingLot.getHasCarSize()+"(辆)|"+relaxSize+"(个)|\n" ;
            allSize+=parkingLot.getSize();
            allhasCarSize+=parkingLot.getHasCarSize();
        }
        int allRelaxSize = allSize-allhasCarSize;
        parkingDetails +="\n" +
                "总车位："+allSize+"(个)\n" +
                "停车总量："+allhasCarSize+"（辆）\n" +
                "总剩余车位："+allRelaxSize+"（个）";
        response.send(parkingDetails);
        return forwardPath;
    }
}
