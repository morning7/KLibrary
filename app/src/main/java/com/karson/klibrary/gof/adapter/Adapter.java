package com.karson.klibrary.gof.adapter;

/**
 * 对象适配器
 */
public class Adapter implements TriplePin {

    private final DualPin mDualPin;

    public Adapter(DualPin dualPin) {
        mDualPin = dualPin;
    }

    @Override
    public void electrify(int l, int n, int e) {
        mDualPin.electrify(l, n);
    }
}
