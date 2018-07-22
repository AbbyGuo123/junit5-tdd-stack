package shell;

import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.ParkingLot;
import com.thoughtworks.tdd.sell.Request;
import com.thoughtworks.tdd.sell.Response;

import com.thoughtworks.tdd.sell.controller.DeleteParkController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
public class DeleteParkTest {
    private Request request;
    private Response response;
    private DeleteParkController deleteParkController;
    private ParkingBoy parkingBoy;

    @BeforeEach
    void setUp() {
        request = mock(Request.class);
        response = mock(Response.class);
        parkingBoy = mock(ParkingBoy.class);
        deleteParkController = new DeleteParkController(request, response, parkingBoy);
    }

    @Test
    public void should_return_deleteFaill_when_call_deletePark_input_the_park_has_car(){
        when(request.getCommand()).thenReturn("111");
        ParkingLot parkingLot1 = new ParkingLot("111","111",1,1);
        List<ParkingLot> lotList = new ArrayList<>();
        lotList.add(parkingLot1);
        when(parkingBoy.getPakingLotList()).thenReturn(lotList);
        String forwardPath = deleteParkController.proccess();
        verify(response).send("此停车场中，依然停有汽车，无法删除！");
        assertThat(forwardPath,is("forward:main"));

    }

    @Test
    public void should_return_deleteFail_when_call_deletePark_input_not_true_the_park(){
        when(request.getCommand()).thenReturn("111");
        when(parkingBoy.getPakingLotList().stream().filter(parkingLot -> parkingLot.getId().equals("111")).collect(Collectors.toList())).thenReturn(new ArrayList<>());
        String forwardPath = deleteParkController.proccess();
        verify(response).send("停车场添加失败，原因：此停车场不存在！");
        assertThat(forwardPath,is("forward:main"));
    }

    @Test
    public void should_return_deleteSuccess_when_call_deletePark_input_the_true_not_hasCar_park(){
        when(request.getCommand()).thenReturn("111");
        ParkingLot parkingLot1 = new ParkingLot("111","111",1,0);
        List<ParkingLot> lotList = new ArrayList<>();
        lotList.add(parkingLot1);
        when(parkingBoy.getPakingLotList()).thenReturn(lotList);
        String forwardPath = deleteParkController.proccess();
        verify(response).send("停车场删除成功！");
        assertThat(forwardPath,is("forward:main"));

    }

}
