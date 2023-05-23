package com.karson.klibrary.gof.command;

public class FlashCommand implements Command {

    private final Bulb mBulb;
    private volatile boolean neoRun = false;

    public FlashCommand(Bulb bulb) {
        mBulb = bulb;
    }

    @Override
    public void positive() {
        if (!neoRun) {
            neoRun = true;
            System.out.println("霓虹灯闪烁任务启动");
            new Thread(() -> {
                while (neoRun) {
                    try {
                        mBulb.on();
                        Thread.sleep(500);
                        mBulb.off();
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    public void negative() {
        neoRun = false;
        System.out.println("霓虹灯闪烁任务结束");
    }
}
