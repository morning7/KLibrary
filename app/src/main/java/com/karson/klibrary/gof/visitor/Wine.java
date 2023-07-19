package com.karson.klibrary.gof.visitor;

import java.time.LocalDate;

public class Wine extends Product {

    public Wine(String name, LocalDate date, float price) {
        super(name, date, price);
    }
}
