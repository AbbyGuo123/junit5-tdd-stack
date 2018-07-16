package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkingManagerTest {
    @Test
    public void should_printParkingList_when_call_parkingDetails(){
        ParkingLot parkingLot1 = new ParkingLot("001","西南停车场",28,8);
        ParkingLot parkingLot2 = new ParkingLot("002","西南停车场",12,8);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        ParkingManager parkingManager = new ParkingManager(parkingLotList);
        String parkingDetails = parkingManager.generateParkingDetails();
        assertThat(parkingDetails, is(
                "|停车场ID|名称|车位|已停车辆|剩余车位|\n" +
                "======================================\n" +
                "|001|西南停车场|28(个)|8(辆)|20(个)|\n" +
                "|002|西南停车场|12(个)|8(辆)|4(个)|\n" +
                "\n" +
                "总车位：40(个)\n" +
                "停车总量：16（辆）\n" +
                "总剩余车位：24（个）"));
    }


}
