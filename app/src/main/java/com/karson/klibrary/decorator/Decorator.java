package com.karson.klibrary.decorator;

public class Decorator implements Showable {
    private Showable mShowable;

    public Decorator(Showable showable) {
        mShowable = showable;
    }

    @Override
    public void show() {
        System.out.print("打粉底<");
        mShowable.show();
        System.out.print(">");
    }
}
