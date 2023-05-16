package com.karson.klibrary.gof.factory;

import com.karson.klibrary.gof.factory.bean.Car;
import com.karson.klibrary.gof.factory.bean.XmCar;
import com.karson.klibrary.gof.factory.listener.ICarFactory;

public class XmCarFactory implements ICarFactory {
    @Override
    public Car create() {
        return new XmCar();
    }
}
