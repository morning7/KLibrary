package com.karson.klibrary.gof.factory;

import com.karson.klibrary.factory.listener.ICarFactory;
import com.karson.klibrary.factory.listener.IComputerFactory;
import com.karson.klibrary.factory.listener.IFactory;
import com.karson.klibrary.factory.listener.IPhoneFactory;

public class HwFactory implements IFactory {
    @Override
    public IComputerFactory createComputerFactory() {
        return new HwComputerFactory();
    }

    @Override
    public IPhoneFactory createPhoneFactory() {
        return new HwPhoneFactory();
    }

    @Override
    public ICarFactory createCarFactory() {
        return new HwCarFactory();
    }
}
