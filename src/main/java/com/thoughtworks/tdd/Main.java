package com.thoughtworks.tdd;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.print("1. 停车\n" +
                "2. 取车 \n" +
                "请输入您要进行的操作：");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        




        System.out.print(input);
    }

    public boolean checkInput(int input) {
        return input==1||input==2;
    }
}
