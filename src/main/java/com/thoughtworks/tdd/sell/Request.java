package com.thoughtworks.tdd.sell;

import java.util.Scanner;

public class Request {
    String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
    public String next(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        return input;
    }
}
