package com.thoughtworks.tdd;

import com.thoughtworks.tdd.Controller.ParkController;
import com.thoughtworks.tdd.model.Request;

public class ParkRouter {
    com.thoughtworks.tdd.Controller.ParkController ParkController;
    String currentPage ;

    public ParkRouter(ParkController ParkController, String currentPage) {
        this.ParkController = ParkController;
        this.currentPage = currentPage;
        forwardMainPage();
    }

    public void forwardMainPage(){
        ParkController.mainPage();
    }

    public void handleCommand(Request request){
        switch (currentPage){
            case "main":
                currentPage = handleMainPage(currentPage,request.getCommand());
                break;
            case "park":
                currentPage = handleParkPage();
                forwardMainPage();
                break;
            case "unpark":
                currentPage = handleUnParkPage();
                forwardMainPage();
                break;

        }
        if(currentPage.equals("isFull")) {
            forwardMainPage();
            currentPage = "main";
        }

    }

    public String handleMainPage(String status,String commands){
        switch (commands){
            case "1":
                status = ParkController.parkpage();
                break;
            case "2":
                ParkController.unparkPage();
                status = "unpark";
                break;
            default:
                ParkController.Not();
                break;
        }
        return status;
    }


    public String handleParkPage(){
        ParkController.park();
        currentPage = "main";
        return currentPage;
    }

    public String handleUnParkPage(){
        ParkController.unpark();
        currentPage = "main";
        return currentPage;
    }

}
