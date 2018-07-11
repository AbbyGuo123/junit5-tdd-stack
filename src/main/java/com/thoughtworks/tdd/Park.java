package com.thoughtworks.tdd;

public class Park {
    int size;
    public Park(int size) {
        this.size = size;
    }

    public boolean parking(Car car) {
        if(size>0)
            return true;
        else
            return false;
    }
}
