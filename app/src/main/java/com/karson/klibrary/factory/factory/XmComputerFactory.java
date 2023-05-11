package com.karson.klibrary.factory.factory;

import com.karson.klibrary.factory.listener.IComputerFactory;
import com.karson.klibrary.factory.bean.Computer;
import com.karson.klibrary.factory.bean.XmComputer;

public class XmComputerFactory implements IComputerFactory {

    @Override
    public Computer create() {
        return new XmComputer();
    }
}
