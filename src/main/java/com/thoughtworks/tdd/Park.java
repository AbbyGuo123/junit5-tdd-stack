package com.thoughtworks.tdd;

public class Park {
    String id;
    int allNumber;
    int relaxNumber;


    public Park(String id, int allNumber, int relaxNumber) {
        this.id = id;
        this.allNumber = allNumber;
        this.relaxNumber = relaxNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAllNumber() {
        return allNumber;
    }

    public void setAllNumber(int allNumber) {
        this.allNumber = allNumber;
    }

    public int getRelaxNumber() {
        return relaxNumber;
    }

    public void setRelaxNumber(int relaxNumber) {
        this.relaxNumber = relaxNumber;
    }

    public boolean hasPack() {
        return this.relaxNumber>0;
    }
}
