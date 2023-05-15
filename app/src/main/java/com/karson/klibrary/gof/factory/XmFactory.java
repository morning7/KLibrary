package com.karson.klibrary.gof.factory;

import com.karson.klibrary.factory.listener.ICarFactory;
import com.karson.klibrary.factory.listener.IComputerFactory;
import com.karson.klibrary.factory.listener.IFactory;
import com.karson.klibrary.factory.listener.IPhoneFactory;

public class XmFactory implements IFactory {
    @Override
    public IComputerFactory createComputerFactory() {
        return new XmComputerFactory();
    }

    @Override
    public IPhoneFactory createPhoneFactory() {
        return new XmPhoneFactory();
    }

    @Override
    public ICarFactory createCarFactory() {
        return new XmCarFactory();
    }
}
