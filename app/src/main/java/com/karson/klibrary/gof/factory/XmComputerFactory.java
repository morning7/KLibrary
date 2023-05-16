package com.karson.klibrary.gof.factory;

import com.karson.klibrary.gof.factory.listener.IComputerFactory;
import com.karson.klibrary.gof.factory.bean.Computer;
import com.karson.klibrary.gof.factory.bean.XmComputer;

public class XmComputerFactory implements IComputerFactory {

    @Override
    public Computer create() {
        return new XmComputer();
    }
}
