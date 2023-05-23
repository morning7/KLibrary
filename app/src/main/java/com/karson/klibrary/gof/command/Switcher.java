package com.karson.klibrary.gof.command;

public class Switcher {
    private Command mCommand;

    public void setCommand(Command command) {
        mCommand = command;
    }


//    private Bulb mBulb;
//
//    public Switcher(Bulb bulb) {
//        mBulb = bulb;
//    }
//
//    public void buttonPush() {
//        System.out.println("按下按钮");
//        mBulb.on();
//    }
//
//    public void buttonPop() {
//        System.out.println("弹起按钮");
//        mBulb.off();
//    }
}
