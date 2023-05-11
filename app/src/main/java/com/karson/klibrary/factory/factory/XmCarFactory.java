package com.karson.klibrary.factory.factory;

import com.karson.klibrary.factory.bean.Car;
import com.karson.klibrary.factory.bean.HwCar;
import com.karson.klibrary.factory.bean.XmCar;
import com.karson.klibrary.factory.listener.ICarFactory;

public class XmCarFactory implements ICarFactory {
    @Override
    public Car create() {
        return new XmCar();
    }
}
