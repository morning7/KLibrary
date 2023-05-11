package com.karson.klibrary.factory.factory;

import com.karson.klibrary.factory.listener.IComputerFactory;
import com.karson.klibrary.factory.bean.Computer;
import com.karson.klibrary.factory.bean.HwComputer;

public class HwComputerFactory implements IComputerFactory {

    @Override
    public Computer create() {
        return new HwComputer();
    }
}
