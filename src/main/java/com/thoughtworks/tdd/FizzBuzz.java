package com.thoughtworks.tdd;

public class FizzBuzz {
    public String fizzBuzzWithNumber(int number) {
        StringBuffer result = new StringBuffer();
        if(number % 3 == 0){
            result.append("Fizz");
        }
        if(number % 5 == 0){
            result.append("Buzz");
        }
        if(number % 7 == 0){
            result.append("Whizz");
        }
        return result.toString().equals("")?String.valueOf(number):result.toString();
    }
}