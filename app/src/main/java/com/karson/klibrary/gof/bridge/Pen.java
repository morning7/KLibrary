package com.karson.klibrary.gof.bridge;

public abstract class Pen {

    protected Ruler mRuler;

    public Pen(Ruler ruler) {
        mRuler = ruler;
    }

    protected abstract void getColor();

    protected abstract void draw();
}
