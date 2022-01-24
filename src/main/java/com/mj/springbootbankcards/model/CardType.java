package com.mj.springbootbankcards.model;

public enum CardType {

    CREDIT("Кредитная"), DEBIT("Дебетовая");

    private final String name;

    CardType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
