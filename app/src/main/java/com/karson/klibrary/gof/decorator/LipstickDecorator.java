package com.karson.klibrary.gof.decorator;

public class LipstickDecorator extends AbstractDecorator {
    public LipstickDecorator(Showable showable) {
        super(showable);
    }

    @Override
    public void show() {
        System.out.print("涂口红<");
        super.show();
        System.out.print(">");
    }
}
