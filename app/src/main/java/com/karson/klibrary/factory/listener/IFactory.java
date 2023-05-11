package com.karson.klibrary.factory.listener;

public interface IFactory {

    IComputerFactory createComputerFactory();

    IPhoneFactory createPhoneFactory();

    ICarFactory createCarFactory();
}
