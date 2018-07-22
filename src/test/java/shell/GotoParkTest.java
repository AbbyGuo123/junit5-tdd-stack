package shell;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;
import com.thoughtworks.tdd.sell.controller.GotoParkingController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class GotoParkTest {
    private Request request;
    private Response response;
    private GotoParkingController gotoParkingController;
    private ParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        parkingBoy = mock(ParkingBoy.class);
        gotoParkingController = new GotoParkingController(request, response, parkingBoy);
    }

    @Test
    public void should_send_correct_page_info_when_parking_lot_not_full() {

        //given
        when(parkingBoy.allParkIsFull()).thenReturn(false);

        //when
        String forwardPath = gotoParkingController.proccess();

        //then
        verify(response).send("请输入车牌号:");
        assertThat(forwardPath, not(containsString("forward:")));
    }

    @Test
    public void should_send_wrong_info_and_forward_main_page_when_parking_lot_is_full() {
        //given
        when(parkingBoy.allParkIsFull()).thenReturn(true);

        //when
        String forwardPath = gotoParkingController.proccess();

        //then
        verify(response).send("车库已满，无法停车！");
        assertThat(forwardPath, containsString("forward:"));
    }
}
