package shell;

import com.thoughtworks.tdd.core.Car;
import com.thoughtworks.tdd.core.NotTrueReceiptException;
import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.Receipt;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;
import com.thoughtworks.tdd.sell.controller.ParkingController;
import com.thoughtworks.tdd.sell.controller.UnParkingController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class unParkTest {
    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;
    private UnParkingController unParkingController;

    @BeforeEach
    void setup(){
        request = mock(Request.class);
        response = mock(Response.class);
        parkingBoy = mock(ParkingBoy.class);
        unParkingController = new UnParkingController(request,response,parkingBoy);
    }

    @Test
    public void should_return_success_when_call_unpark_input_true_reciept(){
        //given
        Receipt receipt = new Receipt("1233444");
        Car car = new Car("1");
        when(request.getCommand()).thenReturn(receipt.getUuid());
        when(parkingBoy.unPark(receipt)).thenReturn(car);

        //when
        String forwardPath = unParkingController.proccess();

        //then
        verify(response).send("车已取出，您的车牌号是: "+car.getId());
        assertThat(forwardPath,is("forward:main"));


    }

    @Test
    public void should_return_fail_when_call_unpark_input_false_receipt(){
        //given
        Receipt receipt = new Receipt("1111");
        Car car = new Car("1");
        when(request.getCommand()).thenReturn("1111");
        when(parkingBoy.unPark(receipt)).thenThrow(new NotTrueReceiptException());

        //when
        String forwardPath = unParkingController.proccess();

        //then
        verify(response).send("非法小票，无法取出车，请查证后再输");
        assertThat(forwardPath,is("forward:main"));
    }

}
