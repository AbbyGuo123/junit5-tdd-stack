package com.thoughtworks.tdd;

import com.thoughtworks.tdd.Controller.ParkManagerController;
import com.thoughtworks.tdd.model.Request;

public class ParkManagerRouter {
    ParkManagerController parkManagerController;
    String currentPage ;

    public ParkManagerRouter(ParkManagerController parkManagerController, String currentPage) {
        this.parkManagerController = parkManagerController;

        this.currentPage = currentPage;
        forwardMainPage();
    }

    public void forwardMainPage(){
        parkManagerController.mainPage();
    }

    //主页面
    public String handleMainPage(String status, String commands){
        switch (commands){
            case "1":
                parkManagerController.main2Page();
                status = "main2";
                break;
            case "2":
                parkManagerController.packManagerPage();
                status = "parkManager";
                break;
            default:
                parkManagerController.Not();
                break;
        }
        return status;
    }
    //停车取车页面
    public String handleMain2Page(String status, String commands){
        switch (commands){
            case "1":
                status = parkManagerController.parkpage();
                break;
            case "2":
                parkManagerController.unparkPage();
                status = "unpark";
                break;
            default:
                parkManagerController.Not();
                break;
        }
        return status;
    }
    //车场管理页面
    public String handleMain3Page(String status, String commands){
        switch (commands){
            case "1":
                handleShowParkDetails();
                forwardMainPage();
                status = "main";
                break;
            case "2":
                parkManagerController.addParkByNameAndSizePage();
                status="addParkByNameAndSize";
                break;
            case "3":
                parkManagerController.deleteParkByIdPage();
                status = "deleteParkById";
                break;
            default:
                parkManagerController.Not();
                break;
        }
        return status;
    }

    public void handleCommand(Request request){
        switch (currentPage){
            case "main":
                currentPage = handleMainPage(currentPage,request.getCommand());
                break;
            case "main2":
                currentPage = handleMain2Page(currentPage,request.getCommand());
                break;
            case "park":
                currentPage = handleParkPage();
                forwardMainPage();
                break;
            case "unpark":
                currentPage = handleUnParkPage();
                forwardMainPage();
                break;
            case "parkManager":
                currentPage = handleMain3Page(currentPage,request.getCommand());
                break;
            case "deleteParkById":
                currentPage = handleDeleteParkPage();
                forwardMainPage();
                break;
            case "addParkByNameAndSize":
                currentPage=handleAddParkPage();

        }
        if(currentPage.equals("isFull")) {
            forwardMainPage();
            currentPage = "main";
        }

    }


    public String handleParkPage(){
        parkManagerController.park();
        currentPage = "main";
        return currentPage;
    }

    public String handleUnParkPage(){
        parkManagerController.unpark();
        currentPage = "main";
        return currentPage;
    }

    public String handleDeleteParkPage(){
        parkManagerController.deleteParkingById();
        currentPage = "main";
        return currentPage;
    }
    public String handleAddParkPage(){
        parkManagerController.addParkingLotByNameAndSize();
        currentPage = "main";
        return currentPage;
    }

    public String handleShowParkDetails(){
        parkManagerController.generateParkingDetails();
        currentPage = "main";
        return currentPage;
    }
}
