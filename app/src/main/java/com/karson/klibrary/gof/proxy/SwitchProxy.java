package com.karson.klibrary.gof.proxy;

/**
 * 交换机
 */
public class SwitchProxy implements Intranet {
    @Override
    public void fileAccess(String path) {
        System.out.println("访问内网");
    }
}
