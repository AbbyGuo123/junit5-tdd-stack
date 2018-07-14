package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.*;

public class MainTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private String systemOut() {
        return outContent.toString();
    }

    @Test
    public void should_Print__park_success_when_call_inputPark_input_1234(){

        PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        Car car = mock(Car.class);

        UUID receiptId = UUID.fromString("fef8e384-8738-11e8-adc0-fa7ae01bbebc");
        Receipt receipt = new Receipt(receiptId);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        when(parkingBoy.allParkIsFull()).thenReturn(false);
        when(parkingBoy.parking(Mockito.any())).thenReturn(receipt);

        String input ="1234";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Computer computer = new Computer();
        computer.inputPark(parkingBoy);
        verify(parkingBoy).parking(Mockito.any());

        verify(out).print("停车成功，您的小票是：\n" + "fef8e384-8738-11e8-adc0-fa7ae01bbebc");


    }

    @Test
    public void should_Print_park_fail_when_call_inputPark_input(){

        PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        Car car = mock(Car.class);

        UUID receiptId = UUID.fromString("fef8e384-8738-11e8-adc0-fa7ae01bbebc");
        Receipt receipt = new Receipt(receiptId);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        when(parkingBoy.allParkIsFull()).thenReturn(true);
        when(parkingBoy.parking(Mockito.any())).thenReturn(receipt);

        Computer computer = new Computer();
        computer.inputPark(parkingBoy);

        verify(out).print("车已停满，请晚点再来");


    }


    @Test
    public void should_Print_unpark_success_when_call_inputPark_input_true_reciept(){

        PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        Car car = mock(Car.class);
        UUID receiptId = UUID.fromString("fef8e384-8738-11e8-adc0-fa7ae01bbebc");
        Receipt receipt = new Receipt(receiptId);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        when(parkingBoy.allParkIsFull()).thenReturn(false);
        when(parkingBoy.parking(Mockito.any())).thenReturn(receipt);


        String input ="1234";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Computer computer = new Computer();
        computer.inputPark(parkingBoy);


        Car car1 = new Car("1234");
        when(parkingBoy.unPark(receipt)).thenReturn(car1);
        String input2 ="fef8e384-8738-11e8-adc0-fa7ae01bbebc";
        InputStream in2 = new ByteArrayInputStream(input2.getBytes());
        System.setIn(in2);
        when(parkingBoy.unPark(Mockito.any())).thenReturn(car1);
        computer.inputUnpark(parkingBoy);


        verify(parkingBoy).parking(Mockito.any());
        verify(parkingBoy).unPark(Mockito.any());

        verify(out).print("车已取出，您的车牌号是: "+car1.getId()+"\n");


    }


    @Test
    public void should_Print_unpark_fail_when_call_inputPark_input_used_reciept(){

        PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        Car car = mock(Car.class);
        UUID receiptId = UUID.fromString("fef8e384-8738-11e8-adc0-fa7ae01bbebc");
        Receipt receipt = new Receipt(receiptId);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        when(parkingBoy.allParkIsFull()).thenReturn(false);
        when(parkingBoy.parking(Mockito.any())).thenReturn(receipt);


        String input ="1234";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Computer computer = new Computer();
        computer.inputPark(parkingBoy);


        Car car1 = new Car("1234");
        when(parkingBoy.unPark(receipt)).thenReturn(car1);
        String input2 ="fef8e384-8738-11e8-adc0-fa7ae01bbebc";
        InputStream in2 = new ByteArrayInputStream(input2.getBytes());
        System.setIn(in2);
        when(parkingBoy.unPark(Mockito.any())).thenThrow(new NotTrueReceiptException());
        computer.inputUnpark(parkingBoy);


        verify(parkingBoy).parking(Mockito.any());
        verify(parkingBoy).unPark(Mockito.any());

        verify(out).print("非法小票，无法取出车，请查证后再输\n");


    }

    @Test
    public void should_call_inputPark_when_call_inputPark_input_1(){


        PrintStream out = mock(PrintStream.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        when(parkingBoy.allParkIsFull()).thenReturn(true);
        System.setOut(out);

        Computer computer = new Computer();
        String input1 ="1";
        InputStream in1 = new ByteArrayInputStream(input1.getBytes());
        System.setIn(in1);
        computer.inputOutput(parkingBoy);

        verify(parkingBoy).allParkIsFull();
    }
    /*
    @Test
    public void should_call_inputUnPark_when_call_inputPark_input_2(){


        PrintStream out = mock(PrintStream.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        when(parkingBoy.unPark(Mockito.any())).thenReturn(new Car());
        System.setOut(out);

        Computer computer = new Computer();
        String input1 ="2\r1234";
        InputStream in1 = new ByteArrayInputStream(input1.getBytes());
        System.setIn(in1);

        when(parkingBoy.unPark(Mockito.any())).thenThrow(new NotTrueReceiptException());
        computer.inputOutput(parkingBoy);




        verify(parkingBoy).unPark(Mockito.any());
    }
*/
    @Test
    public void should_call_Error_when_call_inputPark_input_2(){


        PrintStream out = mock(PrintStream.class);
        ParkingBoy parkingBoy = mock(ParkingBoy.class);
        when(parkingBoy.unPark(Mockito.any())).thenReturn(new Car());
        System.setOut(out);

        Computer computer = new Computer();
        String input1 ="21234";
        InputStream in1 = new ByteArrayInputStream(input1.getBytes());
        System.setIn(in1);

        when(parkingBoy.unPark(Mockito.any())).thenThrow(new NotTrueReceiptException());
        computer.inputOutput(parkingBoy);

        verify(out).print("非法指令，请查证后再输");

    }


}
