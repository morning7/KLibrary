package com.karson.klibrary.gof.factory.listener;

public interface IFactory {

    IComputerFactory createComputerFactory();

    IPhoneFactory createPhoneFactory();

    ICarFactory createCarFactory();
}
