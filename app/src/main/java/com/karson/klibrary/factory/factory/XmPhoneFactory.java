package com.karson.klibrary.factory.factory;

import com.karson.klibrary.factory.bean.Phone;
import com.karson.klibrary.factory.bean.XmPhone;
import com.karson.klibrary.factory.listener.IPhoneFactory;

public class XmPhoneFactory implements IPhoneFactory {
    @Override
    public Phone create() {
        return new XmPhone();
    }
}
