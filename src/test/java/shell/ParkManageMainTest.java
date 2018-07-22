package shell;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;
import com.thoughtworks.tdd.sell.controller.ParkManagerMainController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParkManageMainTest {
    private Request request;
    private Response response;
    private ParkingBoy parkingBoy;
    private ParkManagerMainController parkManagerMainController;

    @BeforeEach
    void setUp(){
        request = mock(Request.class);
        response = mock(Response.class);
        parkingBoy =mock(ParkingBoy.class);
        parkManagerMainController = new ParkManagerMainController(request,response,parkingBoy);
    }

    @Test
    public void should_return_page_when_call_park_main(){
        String forwardPath = parkManagerMainController.proccess();
        verify(response).send("1.查看停车场详情\n" +
                "2.添加停车场\n" +
                "3.删除停车场");
        assertThat(forwardPath,not("forward:"));
    }
}
