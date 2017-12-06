package com.samples.rae.srv.domain;

public class Account {

    private String name;
    private float amount;

    public Account(String name, float amount) {
        this.name = name;
        this.amount = amount;
    }

    public Account() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
