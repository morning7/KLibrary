package com.karson.klibrary.gof.bridge;

public class BlackPen extends Pen {

    public BlackPen(Ruler ruler) {
        super(ruler);
    }

    @Override
    protected void getColor() {
        System.out.print("黑");
    }

    @Override
    protected void draw() {
        getColor();
        mRuler.shape();
    }
}
