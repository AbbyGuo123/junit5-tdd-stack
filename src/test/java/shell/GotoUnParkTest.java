package shell;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;
import com.thoughtworks.tdd.sell.controller.GotoUnParkController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class GotoUnParkTest {
    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;
    private GotoUnParkController gotoUnParkController;

    @BeforeEach
    void setup(){
        request = mock(Request.class);
        response = mock(Response.class);
        parkingBoy =mock(ParkingBoy.class);
        gotoUnParkController = new GotoUnParkController(request,response,parkingBoy);
    }

    @Test
    public void should_send_correct_page_info_when_call_unpark(){
        String forwardPath = gotoUnParkController.proccess();
        verify(response).send("请输入小票编号：");
        assertThat(forwardPath,not(containsString("forward:")));
    }
}
