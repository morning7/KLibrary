package com.karson.klibrary.gof.bridge;

public class BridgeClient {

    public static void main(String[] args) {
        new BlackPen(new CircleRuler()).draw();
    }
}
