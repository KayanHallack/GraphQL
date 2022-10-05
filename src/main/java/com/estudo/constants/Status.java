package com.estudo.constants;

public enum Status {

    PENDING("PENDING"),
    APPROVED("APPROVED"),
    COMPLETED("COMPLETED");

    private String value;

    Status(String value) {
        this.value = value;
    }
}
