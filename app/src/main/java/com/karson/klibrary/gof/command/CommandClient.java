package com.karson.klibrary.gof.command;

public class CommandClient {

    public static void main(String[] args) {
//        Switcher switcher = new Switcher(new Bulb());
//        switcher.buttonPush();
//        switcher.buttonPop();

//        Switcher switcher = new Switcher();
//        Command switchCommand = new SwitchCommand(new Bulb());
//        switcher.setCommand(switchCommand);
//        switchCommand.positive();

        Switcher switcher = new Switcher();
        Command flashCommand = new FlashCommand(new Bulb());
        switcher.setCommand(flashCommand);
        flashCommand.positive();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flashCommand.negative();

    }
}
