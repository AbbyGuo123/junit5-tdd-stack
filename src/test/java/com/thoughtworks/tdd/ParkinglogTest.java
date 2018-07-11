package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkinglogTest {
    @Test
    public void should_return_true_when_call_paking_given_input_Car(){
        Car car = new Car();
        Park park = new Park(1);
        boolean result = park.parking(car);
        assertThat(result, is(true));
    }
    @Test
    public void should_return_false_when_call_paking_given_input_Car(){
        Car car = new Car();
        Park park = new Park(0);
        boolean result = park.parking(car);
        assertThat(result, is(false));
    }

}
