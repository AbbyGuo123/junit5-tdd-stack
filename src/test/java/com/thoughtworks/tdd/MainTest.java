package com.thoughtworks.tdd;

import com.thoughtworks.tdd.Controller.ParkController;
import com.thoughtworks.tdd.model.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainTest {

    @Test
    public void should_send_Pls_input_carId_when_call_parkpage_input_not_allParkisFull(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        when(parkingBoy.allParkIsFull()).thenReturn(false);
        ParkController ParkController = new ParkController(request,response,parkingBoy);
        ParkController.parkpage();
        verify(response).send("请输入车牌号:");
    }

    @Test
    public void should_send_the_park_isFull_when_call_parkpage_input_allParkisFull(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        when(parkingBoy.allParkIsFull()).thenReturn(true);
        ParkController ParkController = new ParkController(request,response,parkingBoy);
        ParkController.parkpage();
        verify(response).send("车已停满，请晚点再来");
    }

    @Test
    public void should_send_success_park_when_call_park(){
        Request request = mock(Request.class);
        Response response = mock(Response.class);
        Receipt receipt = new Receipt("fef8e384-8738-11e8-adc0-fa7ae01bbebc");
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        when(parkingBoy.parking(Mockito.any())).thenReturn(receipt);
        ParkController ParkController = new ParkController(request,response,parkingBoy);
        ParkController.park();
        verify(response).send("停车成功，您的小票是：\n" + receipt.getUuid());
    }

    @Test
    public void should_send_success_unpark_when_call_unpark_input_receiptId_isTrue(){
        Car car = new Car("123");
        Request request = mock(Request.class);
        when(request.getCommand()).thenReturn("fef8e384-8738-11e8-adc0-fa7ae01bbebc");
        Response response = mock(Response.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        when(parkingBoy.unPark(Mockito.any())).thenReturn(car);
        ParkController ParkController = new ParkController(request,response,parkingBoy);
        ParkController.unpark();
        verify(response).send("车已取出，您的车牌号是: "+car.getId());
    }

    @Test
    public void should_send_fail_unpark_when_call_unpark_input_receiptId_isTrue(){
        Car car = new Car("123");
        Request request = mock(Request.class);
        when(request.getCommand()).thenReturn("fef8e384-8738-11e8-adc0-fa7ae01bbebc");
        Response response = mock(Response.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        when(parkingBoy.unPark(Mockito.any())).thenThrow(new NotTrueReceiptException());
        ParkController ParkController = new ParkController(request,response,parkingBoy);
        ParkController.unpark();
        verify(response).send("非法小票，无法取出车，请查证后再输");
    }

}
