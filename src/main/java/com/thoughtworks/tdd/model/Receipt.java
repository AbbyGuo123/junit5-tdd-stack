package com.thoughtworks.tdd.model;

import java.util.UUID;

public class Receipt {
    private String uuid ;

    public Receipt() {
    }

    public Receipt(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
