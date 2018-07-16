package com.thoughtworks.tdd;

public class Router {
    Controller controller ;
    String currentPage ;

    public Router(Controller controller, String currentPage) {
        this.controller = controller;
        this.currentPage = currentPage;
        forwardMainPage();
    }

    public void forwardMainPage(){
        controller.mainPage();
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
                status = controller.parkpage();
                break;
            case "2":
                controller.unparkPage();
                status = "unpark";
                break;
            default:
                controller.Not();
                break;
        }
        return status;
    }


    public String handleParkPage(){
        controller.park();
        currentPage = "main";
        return currentPage;
    }

    public String handleUnParkPage(){
        controller.unpark();
        currentPage = "main";
        return currentPage;
    }

}
