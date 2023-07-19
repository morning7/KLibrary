package com.karson.klibrary.gof.visitor;

import java.time.LocalDate;

public class Candy extends Product {

    public Candy(String name, LocalDate date, float price) {
        super(name, date, price);
    }
}
