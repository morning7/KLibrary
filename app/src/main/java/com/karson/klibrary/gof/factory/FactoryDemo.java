package com.karson.klibrary.gof.factory;

import com.karson.klibrary.gof.factory.listener.IFactory;

public class FactoryDemo {

    public static void main(String[] args) {
//        //小作坊-简单工厂
        SimpleFactory simpleFactory = new SimpleFactory();
        simpleFactory.create(1).show();
        simpleFactory.create(2).show();
//
//        //独立办厂-工厂模式
//        IComputerFactory computerFactory = new HwComputerFactory();
//        computerFactory.create().show();

//        computerFactory = new XmComputerFactory();
//        computerFactory.create().show();

        //多生产线-抽象工厂
        IFactory factory = new HwFactory();
        factory.createComputerFactory().create().show();
        factory.createPhoneFactory().create().show();
        factory.createCarFactory().create().show();

        factory = new XmFactory();
        factory.createComputerFactory().create().show();
        factory.createPhoneFactory().create().show();
        factory.createCarFactory().create().show();
    }
}



