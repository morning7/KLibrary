package com.karson.klibrary.decorator;

public abstract class AbstractDecorator implements Showable {
    protected Showable mShowable;

    public AbstractDecorator(Showable showable) {
        mShowable = showable;
    }

    @Override
    public void show() {
        mShowable.show();
    }
}
