package com.karson.klibrary.gof.visitor;

import java.time.LocalDate;

public class DiscountVisitor implements Visitor {

    private LocalDate billDate;

    public DiscountVisitor(LocalDate billDate) {
        this.billDate = billDate;
        System.out.println("结算日期：" + billDate);
    }


    @Override
    public void visit(Candy candy) {
        System.out.println("糖果 " + candy.getName() + " 打折后");
        float rate = 0;
//        long days = billDate.toEpochDay() - candy.getDate().toEpochDay();
    }

    @Override
    public void visit(Wine wine) {

    }

    @Override
    public void visit(Fruit fruit) {

    }
}
