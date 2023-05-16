package com.karson.klibrary.gof.facade;

/**
 * 门面模式（装饰模式）
 */
public class FacadeClient {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.order();
    }
}


