package com.mj.springbootbankcards.model;

public enum PaymentSystemType {

    VISA("Visa"), MASTERCARD("MasterCard"), MIR("Мир");

    private final String name;

    PaymentSystemType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
