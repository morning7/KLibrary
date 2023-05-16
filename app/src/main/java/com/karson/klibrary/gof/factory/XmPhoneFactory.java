package com.karson.klibrary.gof.factory;

import com.karson.klibrary.gof.factory.bean.Phone;
import com.karson.klibrary.gof.factory.bean.XmPhone;
import com.karson.klibrary.gof.factory.listener.IPhoneFactory;

public class XmPhoneFactory implements IPhoneFactory {
    @Override
    public Phone create() {
        return new XmPhone();
    }
}
