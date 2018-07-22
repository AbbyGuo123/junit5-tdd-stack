package shell;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.Receipt;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;
import com.thoughtworks.tdd.sell.controller.ParkingController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ParkingTest {
    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;
    private ParkingController parkingController;

    @BeforeEach
    void setup(){
        request = mock(Request.class);
        response = mock(Response.class);
        parkingBoy = mock(ParkingBoy.class);
        parkingController = new ParkingController(request,response,parkingBoy);
    }

    @Test
    public void should_send_success_when_call_parking(){

        Receipt receipt = new Receipt();
        when(parkingBoy.parking(Mockito.any())).thenReturn(receipt);
        String forwardPath = parkingController.proccess();
        verify(response).send("停车成功，您的小票是：\n" + receipt.getUuid());
        assertThat(forwardPath,containsString("forward:"));
    }

}
