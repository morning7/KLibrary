package com.karson.klibrary.gof.visitor;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Product {

    private String name;
    private LocalDate date;
    private float price;

    public Product(String name, LocalDate date, float price) {
        this.name = name;
        this.date = date;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
