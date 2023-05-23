package com.karson.klibrary.gof.command;

public class SwitchCommand implements Command {

    private final Bulb mBulb;

    public SwitchCommand(Bulb bulb) {
        mBulb = bulb;
    }

    @Override
    public void positive() {
        mBulb.on();
    }

    @Override
    public void negative() {
        mBulb.off();
    }
}
