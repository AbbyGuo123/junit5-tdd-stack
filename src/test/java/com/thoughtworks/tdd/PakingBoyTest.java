package com.thoughtworks.tdd;

import com.thoughtworks.tdd.model.Car;
import com.thoughtworks.tdd.model.ParkingBoy;
import com.thoughtworks.tdd.model.ParkingLot;
import com.thoughtworks.tdd.model.Receipt;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class PakingBoyTest {

    //单次停车
    @Test
    public void should_be_successfully_given_parking_lot_is_not_full() {
        Receipt receipt = new Receipt();
        Car car = new Car();
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false,false);
        when(parkingLot1.park(car)).thenReturn(receipt);
        List<ParkingLot> pakingLotList = new ArrayList<>();
        pakingLotList.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(pakingLotList);
        try {
            parkingBoy.parking(car);
            verify(parkingLot1).park(car);
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }

    }
    //多次停车，不够车位
    @Test
    public void should_be_failed_given_parking_lot_is_full() {
        Car car = new Car();
        Receipt receipt = new Receipt();
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false,true);
        when(parkingLot1.park(car)).thenReturn(receipt);
        List<ParkingLot> pakingLotList = new ArrayList<>();
        pakingLotList.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(pakingLotList);
        try {
            parkingBoy.parking(car);
            parkingBoy.parking(car);
            verify(parkingLot1).park(car);
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }

    }
    //多次停车
    @Test
    public void should_be_successfully_given_parking_lot_parkinglot_second_is_not_full() {
        Car car = new Car();
        Receipt receipt = new Receipt();
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false,true);
        when(parkingLot2.isFull()).thenReturn(false);
        when(parkingLot1.park(car)).thenReturn(receipt);
        when(parkingLot2.park(car)).thenReturn(receipt);
        List<ParkingLot> pakingLotList = new ArrayList<>();
        pakingLotList.add(parkingLot1);
        pakingLotList.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(pakingLotList);
        try {
            parkingBoy.parking(car);
            parkingBoy.parking(car);
            verify(parkingLot1).park(car);
            verify(parkingLot2).park(car);
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }

    }
    //单次取车
    @Test
    public void should_get_specific_car_when_call_unPark_given_receipt_is_right_by_parkingBoy() {
        Car car = new Car();
        Receipt receipt = new Receipt();
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false);
        when(parkingLot1.park(car)).thenReturn(receipt);
        when(parkingLot1.isTheCarInTheParkingLot(receipt)).thenReturn(true);
        when(parkingLot1.unPark(receipt)).thenReturn(car);
        List<ParkingLot> pakingLotList = new ArrayList<>();
        pakingLotList.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(pakingLotList);
        try {
            receipt = parkingBoy.parking(car);
            assertThat(parkingBoy.unPark(receipt), is(car));
            verify(parkingLot1).park(car);
            verify(parkingLot1).unPark(receipt);
        }catch (NotTrueReceiptException exception){
            fail("the receipt is not true");
        }


    }
    //多次取车
    @Test
    public void should_be_true_when_callpark_2_unPark_the_first_one() {
        Car car1 = new Car();
        Car car2 = new Car();
        Receipt receipt1 = new Receipt();
        Receipt receipt2 = new Receipt();
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false,true);
        when(parkingLot1.park(car1)).thenReturn(receipt1);
        when(parkingLot1.isTheCarInTheParkingLot(receipt1)).thenReturn(true);
        when(parkingLot1.unPark(receipt1)).thenReturn(car1);
        when(parkingLot2.isFull()).thenReturn(false);
        when(parkingLot2.park(car2)).thenReturn(receipt1);
        when(parkingLot2.isTheCarInTheParkingLot(receipt1)).thenReturn(true);
        when(parkingLot2.unPark(receipt1)).thenReturn(car2);

        List<ParkingLot> pakingLotList = new ArrayList<>();
        pakingLotList.add(parkingLot1);
        pakingLotList.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(pakingLotList);
        try {

            receipt1 = parkingBoy.parking(car1);
            receipt2 = parkingBoy.parking(car2);
            parkingBoy.unPark(receipt1);
            parkingBoy.unPark(receipt2);
            verify(parkingLot1).park(car1);
            verify(parkingLot2).park(car2);
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }

    }

    //顺序取车
    @Test
    public void should_be_true_given_parkingBoy_unpark_by_order() {
        Car car1 = new Car();
        Car car2 = new Car();
        Receipt receipt1 = new Receipt();
        Receipt receipt2 = new Receipt();
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false,true,false);
        when(parkingLot1.park(car1)).thenReturn(receipt1,receipt1);
        when(parkingLot1.isTheCarInTheParkingLot(receipt1)).thenReturn(true,true);
        when(parkingLot1.unPark(receipt1)).thenReturn(car1);
        when(parkingLot2.isFull()).thenReturn(false);
        when(parkingLot2.park(car2)).thenReturn(receipt1);
        when(parkingLot2.isTheCarInTheParkingLot(receipt1)).thenReturn(true);
        when(parkingLot2.unPark(receipt1)).thenReturn(car2);
        List<ParkingLot> pakingLotList = new ArrayList<>();
        pakingLotList.add(parkingLot1);
        pakingLotList.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(pakingLotList);
        try {
            receipt1 = parkingBoy.parking(car1);
            receipt2 = parkingBoy.parking(car2);
            parkingBoy.unPark(receipt1);
            receipt1 = parkingBoy.parking(car1);
            assertThat(parkingLot1.isFull(), is(false));
            InOrder inOrder = inOrder(parkingLot1);
            inOrder.verify(parkingLot1).park(car1);
            inOrder.verify(parkingLot1).park(car1);

        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }

    }

    //取过车的票据取车
    @Test
    public void should_be_throw_NotTrueReceiptException_when_callunpark_given_used_receipt() {
        Car car = new Car();
        Receipt receipt = new Receipt();
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false);
        when(parkingLot1.park(car)).thenReturn(receipt);
        when(parkingLot1.isTheCarInTheParkingLot(receipt)).thenReturn(true,false);
        when(parkingLot1.unPark(receipt)).thenReturn(car);
        List<ParkingLot> pakingLotList = new ArrayList<>();
        pakingLotList.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(pakingLotList);
        try {
            receipt = parkingBoy.parking(car);
            parkingBoy.unPark(receipt);
            parkingBoy.unPark(receipt);
            verify(parkingLot1.park(car));
            verify(parkingLot1.unPark(receipt));
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        } catch (NotTrueReceiptException exception) {
            fail("The Reciept is Used");
        }

    }


//
//        @Test
//    public void should_be_true_when_call_check_input_1(){
//        Main Main = new Main();
//        int input = 1;
//        assertThat(Main.checkInput(input),is(true));
//    }
//
//
//    @Test
//    public void should_be_false_when_call_check_input_3(){
//        Main Main = new Main();
//        int input = 3;
//        assertThat(Main.checkInput(input),is(false));
//    }

}
