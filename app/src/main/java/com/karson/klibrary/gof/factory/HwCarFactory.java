package com.karson.klibrary.gof.factory;

import com.karson.klibrary.gof.factory.bean.Car;
import com.karson.klibrary.gof.factory.bean.HwCar;
import com.karson.klibrary.gof.factory.listener.ICarFactory;

public class HwCarFactory implements ICarFactory {
    @Override
    public Car create() {
        return new HwCar();
    }
}
