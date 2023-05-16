package com.karson.klibrary.gof.factory;

import com.karson.klibrary.gof.factory.bean.Computer;
import com.karson.klibrary.gof.factory.bean.HwComputer;
import com.karson.klibrary.gof.factory.bean.XmComputer;

public class SimpleFactory {

    public Computer create(int type) {
        Computer computer;
        switch (type) {
            case 1:
                computer = new HwComputer();
                break;

            case 2:
                computer = new XmComputer();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return computer;
    }
}
