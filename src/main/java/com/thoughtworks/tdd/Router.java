package com.thoughtworks.tdd;

import com.thoughtworks.tdd.Controller.BaseController;

import java.util.HashMap;
import java.util.Map;

public class Router {
    private final String initRoutPath;
    private String currentRoutPath;
    Map<String ,BaseController> routMaps = new HashMap<>();

    public Router(String initRoutPath, String currentRoutPath) {
        this.initRoutPath = initRoutPath;
        this.currentRoutPath = currentRoutPath;
    }

    public void launch(){
        routMaps.get(this.initRoutPath).proccess();
    }
    public void registerRoute(String routePath,BaseController controller){
        routMaps.put(routePath,controller);
    }



}
