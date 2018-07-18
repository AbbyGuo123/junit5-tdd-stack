package com.thoughtworks.tdd;

import com.thoughtworks.tdd.Controller.BaseController;
import com.thoughtworks.tdd.sell.Request;

import java.util.HashMap;
import java.util.Map;

public class Router {
    private final String initRoutPath;
    private String currentRoutPath;
    Map<String ,BaseController> routMaps = new HashMap<>();

    public Router(String initRoutStatus) {
        this.initRoutPath = initRoutStatus;
        this.currentRoutPath = initRoutStatus;
    }

    public void launch(){
        routMaps.get(this.initRoutPath).proccess();
    }
    public void registerRoute(String routePath,BaseController controller){
        routMaps.put(routePath,controller);
    }

    public void processRequest(Request request){
        String routePath = buildLocateRoutePath(request);
        String forwardRouteRule = routMaps.get(routePath).proccess();
        currentRoutPath = routePath;
        if (forwardRouteRule != null && forwardRouteRule.contains("forward:")) {
            currentRoutPath = buildForwardRoutePath(forwardRouteRule);
        }
    }

    private String buildLocateRoutePath(Request request) {

        String subPath = request.getCommand().isEmpty() ? "" : "/" + translateRequestInput(request);
        return currentRoutPath + subPath;
    }

    public String buildForwardRoutePath(String forwardRoutePath){
        String forwardRout = forwardRoutePath.split(":")[1];
        routMaps.get(forwardRout).proccess();
        return forwardRout;
    }

    public String translateRequestInput(Request request){
        if(request.getCommand().equals("1")||request.getCommand().equals("2")){
            return request.getCommand();
        }
        else
            return "*";
    }


}
