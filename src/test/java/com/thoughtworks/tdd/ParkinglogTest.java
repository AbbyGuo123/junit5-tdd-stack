package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;


public class ParkinglogTest {
    @Test
    public void should_park_successfully_given_parking_lot_is_not_full() {
        ParkingLot parkingLot = new ParkingLot(1);

        try {
            parkingLot.park(new Car());
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }

    }
    @Test
    public void should_park_failed_given_parking_lot_is_full(){
        ParkingLot parkingLot = new ParkingLot(0);

        try {
            parkingLot.park(new Car());
            fail("should park successfully");
        } catch (ParkingLotFullException exception) {

        }
    }

    @Test
    public void should_get_specific_car_when_call_unPark_given_receipt_is_right(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car();
        Receipt receipt = parkingLot.park(theCar);

        assertThat(parkingLot.unPark(receipt), is(theCar));

    }
    @Test
    public void should_be_true_when_call_isFull_given_parking_lot_is_full(){
        ParkingLot parkingLot = new ParkingLot(0);

        assertThat(parkingLot.isFull(), is(true));
    }
    @Test
    public void should_be_false_when_call_isFull_given_parking_lot_is_not_full(){
        ParkingLot parkingLot = new ParkingLot(1);

        assertThat(parkingLot.isFull(), is(false));
    }

    @Test
    public void should_be_false_when_call_isFull_given_a_full_parking_lot_take_out_a_car(){
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car();
        Receipt receipt = parkingLot.park(theCar);
        parkingLot.unPark(receipt);

        assertThat(parkingLot.isFull(), is(false));
    }
    public static void shoudNotThrowException(){
//        throw new ParkingLotFullException();
    }
    @Test
    public void should_fail(){

        System.out.println("Hello I am OK");
        try {
            shoudNotThrowException();
        } catch (ParkingLotFullException e) {
            System.out.println("Hello I am in catch");
            fail("it failed");
        }
        System.out.println("Hello I am called");

    }
    @Test
    public void should_be_successfully_given_parking_lot_is_not_full(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        List<ParkingLot> pakingLotList = new ArrayList<>();
        pakingLotList.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(pakingLotList);
        try {
            parkingBoy.parking(new Car());
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }

    }

    @Test
    public void should_be_failed_given_parking_lot_is_full(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        List<ParkingLot> pakingLotList = new ArrayList<>();
        pakingLotList.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(pakingLotList);
        try {
            parkingBoy.parking(new Car());
            parkingBoy.parking(new Car());
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }

    }

    @Test
    public void should_be_successfully_given_parking_lot_parkinglot_second_is_not_full(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> pakingLotList = new ArrayList<>();
        pakingLotList.add(parkingLot1);
        pakingLotList.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(pakingLotList);
        try {
            parkingBoy.parking(new Car());
            parkingBoy.parking(new Car());
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }

    }

    @Test
    public void should_get_specific_car_when_call_unPark_given_receipt_is_right_by_parkingBoy(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        List<ParkingLot> pakingLotList = new ArrayList<>();
        pakingLotList.add(parkingLot1);
        ParkingBoy parkingBoy = new ParkingBoy(pakingLotList);
        Car theCar = new Car();
        Receipt receipt = parkingBoy.parking(theCar);
        assertThat(parkingBoy.unPark(receipt), is(theCar));

    }

    @Test
    public void should_get_specific_car_when_call_unPark_more_given_receipt_is_right_by_parkingBoy(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> pakingLotList = new ArrayList<>();
        pakingLotList.add(parkingLot1);
        pakingLotList.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(pakingLotList);
        try {
            Receipt receipt = parkingBoy.parking(new Car());
            Receipt receipt1 = parkingBoy.parking(new Car());
            parkingBoy.unPark(receipt);
            parkingBoy.unPark(receipt1);
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }

    }

    @Test
    public void should_be_false_given_parking_lot_parkinglot_unpark_is_not_full(){
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        List<ParkingLot> pakingLotList = new ArrayList<>();
        pakingLotList.add(parkingLot1);
        pakingLotList.add(parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(pakingLotList);
        try {
            Receipt receipt = parkingBoy.parking(new Car());
            Receipt receipt1 = parkingBoy.parking(new Car());
            parkingBoy.unPark(receipt);
            assertThat(parkingLot1.isFull(), is(false));

        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }

    }



}
