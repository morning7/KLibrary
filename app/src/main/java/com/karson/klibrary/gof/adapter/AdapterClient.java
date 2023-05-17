package com.karson.klibrary.gof.adapter;

public class AdapterClient {
    public static void main(String[] args) {
//        DualPin dualPinDevice = new TV();
//        TriplePin adapter = new Adapter(dualPinDevice);
        TriplePin adapter = new TVAdapter();
        adapter.electrify(1, 0, -1);
    }
}
