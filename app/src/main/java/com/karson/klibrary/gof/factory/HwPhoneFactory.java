package com.karson.klibrary.gof.factory;

import com.karson.klibrary.gof.factory.bean.HwPhone;
import com.karson.klibrary.gof.factory.bean.Phone;
import com.karson.klibrary.gof.factory.listener.IPhoneFactory;

public class HwPhoneFactory implements IPhoneFactory {
    @Override
    public Phone create() {
        return new HwPhone();
    }
}
