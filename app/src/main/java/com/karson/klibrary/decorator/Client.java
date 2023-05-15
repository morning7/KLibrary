package com.karson.klibrary.decorator;

public class Client {
    public static void main(String[] args) {
//        Showable showable = new Decorator(new Girl());
//        showable.show();

        Showable girl = new LipstickDecorator(new FoundationDecorator(new Girl()));
        girl.show();
    }
}
