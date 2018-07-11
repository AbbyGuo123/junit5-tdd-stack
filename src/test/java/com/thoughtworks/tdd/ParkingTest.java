package com.thoughtworks.tdd;

import jdk.nashorn.internal.objects.NativeJSON;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkingTest {
    @Test
    public void should_return_true_when_call_hasPakingwhere_given_input_parking(){
        //given
        Park park = new Park("1",10,10);
        //when
        boolean hasPack = park.hasPack();
        //then
        assertThat(hasPack ,is(true));
    }

    @Test
    public void should_return_ticket_when_call_parking_given_input_park(){
        //given
        Park park = new Park("1",10,10);
        Car car = new Car("1");
        //when
        Ticket ticket = car.parking(park);
        Ticket ticket1 = new Ticket("1",car,park);
        //then
        assertThat(ticket.id ,is(ticket1.id));
    }
    @Test
    public void should_return_car_when_call_exparking_given_input_tiket(){
        //given
        Car car = new Car("1");
        Person person = new Person("1",car);
        Park park = new Park("1",10,10);
        Ticket ticket = new Ticket("1",car,park);
        //when
        Car car1 = car.exparking(ticket);
        //then
        assertThat(car.id ,is(car1.id));
    }



}
