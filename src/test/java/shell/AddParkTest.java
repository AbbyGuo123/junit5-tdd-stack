package shell;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;
import com.thoughtworks.tdd.sell.controller.AddParkController;
import com.thoughtworks.tdd.sell.controller.GotoParkingController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddParkTest {
    private Request request;
    private Response response;
    private AddParkController addParkController;
    private ParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        parkingBoy = mock(ParkingBoy.class);
        addParkController = new AddParkController(request, response, parkingBoy);
    }

    @Test
    public void should_send_correct_page_info_when_parking_lot_not_full() {

        //given
        when(request.getCommand()).thenReturn("1,2");

        //when
        String forwardPath = addParkController.proccess();

        //then
        verify(response).send("停车场添加成功！");
        assertThat(forwardPath, containsString("forward:"));
    }

}

