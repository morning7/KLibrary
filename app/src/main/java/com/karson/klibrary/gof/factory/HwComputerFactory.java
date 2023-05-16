package com.karson.klibrary.gof.factory;

import com.karson.klibrary.gof.factory.listener.IComputerFactory;
import com.karson.klibrary.gof.factory.bean.Computer;
import com.karson.klibrary.gof.factory.bean.HwComputer;

public class HwComputerFactory implements IComputerFactory {

    @Override
    public Computer create() {
        return new HwComputer();
    }
}
