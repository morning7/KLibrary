package com.karson.klibrary.gof.decorator;

public class FoundationDecorator extends AbstractDecorator {
    public FoundationDecorator(Showable showable) {
        super(showable);
    }

    @Override
    public void show() {
        System.out.print("打粉底<");
        super.show();
        System.out.print(">");
    }
}
