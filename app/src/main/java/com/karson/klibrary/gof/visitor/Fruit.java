package com.karson.klibrary.gof.visitor;

import java.time.LocalDate;

public class Fruit extends Product {

    private float weight;

    public Fruit(String name, LocalDate date, float price, float weight) {
        super(name, date, price);
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
