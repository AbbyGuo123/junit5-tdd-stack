package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ParkinglogTest {
    @Test
    public void should_park_successfully_given_parking_lot_is_not_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        boolean b = parkingLot.isFull();
        try {
            parkingLot.park(mock(Car.class));
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }

    }

    @Test
    public void should_park_failed_given_parking_lot_is_full() {
        ParkingLot parkingLot = new ParkingLot(0);
        try {
            parkingLot.park(mock(Car.class));
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }
    }

    @Test
    public void should_get_specific_car_when_call_unPark_given_receipt_is_right() {
        Car car =mock(Car.class);

        ParkingLot parkingLot = new ParkingLot(1);
        Receipt receipt = parkingLot.park(car);
        assertThat(parkingLot.unPark(receipt), is(car));

    }

    @Test
    public void should_be_true_when_call_isFull_given_parking_lot_is_full() {
        ParkingLot parkingLot = new ParkingLot(0);

        assertThat(parkingLot.isFull(), is(true));
    }

    @Test
    public void should_be_false_when_call_isFull_given_parking_lot_is_not_full() {
        ParkingLot parkingLot = new ParkingLot(1);

        assertThat(parkingLot.isFull(), is(false));
    }

    @Test
    public void should_be_false_when_call_isFull_given_a_full_parking_lot_take_out_a_car() {
        Car car =mock(Car.class);
        ParkingLot parkingLot = new ParkingLot(1);
        Receipt receipt = parkingLot.park(car);
        parkingLot.unPark(receipt);

        assertThat(parkingLot.isFull(), is(false));
    }

    public static void shoudNotThrowException() {
        throw new ParkingLotFullException();
    }

    @Test
    public void should_fail() {

        System.out.println("Hello I am OK");
        try {
            shoudNotThrowException();
        } catch (ParkingLotFullException e) {
            System.out.println("Hello I am in catch");
            fail("it failed");
        }
        System.out.println("Hello I am called");

    }





}

