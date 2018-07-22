package shell;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;
import com.thoughtworks.tdd.sell.controller.ParkMainController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkMainTest {
    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;
    private ParkMainController parkMainController;

    @BeforeEach
    void setUp(){
        request = mock(Request.class);
        response = mock(Response.class);
        parkingBoy =mock(ParkingBoy.class);
        parkMainController = new ParkMainController(request,response,parkingBoy);
    }

    @Test
    public void should_return_page_when_call_park_main(){
        String forwardPath = parkMainController.proccess();
        verify(response).send("1. 停车\n" + "2. 取车 \n" + "请输入您要进行的操作：");
        assertThat(forwardPath,not("forward:"));
    }
}
