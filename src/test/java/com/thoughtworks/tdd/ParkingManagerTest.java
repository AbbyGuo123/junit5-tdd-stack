package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ParkingManagerTest {
    @Test
    public void should_printParkingList_when_call_parkingDetails(){
        ParkingLot parkingLot1 = new ParkingLot("001","西南停车场",28,8);
        ParkingLot parkingLot2 = new ParkingLot("002","西南停车场",12,8);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        parkingLotList.add(parkingLot2);
        ParkingManager parkingManager = new ParkingManager(parkingLotList,new Response());
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

    @Test
    public void should_print_success_when_call_addParking_input_nameAndSize(){
        Response response = mock(Response.class);

        ParkingManager parkingManager = new ParkingManager(new ArrayList<>(),response);
        parkingManager.addParkingLotByNameAndSize("西南停车场",10);
        verify(response).send("停车场添加成功！");
    }

    @Test
    public void should_print_success_when_call_deleteParking_input_parkingId(){
        Response response = mock(Response.class);
        ParkingLot parkingLot1 = new ParkingLot("001","西南停车场",28,0);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        ParkingManager parkingManager = new ParkingManager(parkingLotList,response);

        parkingManager.deleteParkingById("001");
        verify(response).send("停车场删除成功！");
    }

    @Test
    public void should_print_fail_by_not_have_this_park_when_call_deleteParking_input_parkingId(){
        Response response = mock(Response.class);
        ParkingLot parkingLot1 = new ParkingLot("001","西南停车场",28,8);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        ParkingManager parkingManager = new ParkingManager(parkingLotList,response);

        parkingManager.deleteParkingById("002");
        verify(response).send("停车场添加失败，原因：此停车场不存在！");
    }

    @Test
    public void should_print_fail_by_the_park_has_car_when_call_deleteParking_input_parkingId(){
        Response response = mock(Response.class);
        ParkingLot parkingLot1 = new ParkingLot("001","西南停车场",28,8);
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot1);
        ParkingManager parkingManager = new ParkingManager(parkingLotList,response);

        parkingManager.deleteParkingById("001");
        verify(response).send("此停车场中，依然停有汽车，无法删除！");
    }
}
