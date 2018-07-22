package com.thoughtworks.tdd.core;

import java.util.Objects;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Objects.equals(this.uuid.toString(), receipt.uuid.toString());
    }
}
