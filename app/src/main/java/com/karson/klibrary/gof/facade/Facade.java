package com.karson.klibrary.gof.facade;

public class Facade {
    public VegVendor mVegVendor;
    public Chef mChef;
    public Waiter mWaiter;
    public Cleaner mCleaner;

    public Facade() {
        this.mVegVendor = new VegVendor();
        mVegVendor.purchase();
        this.mChef = new Chef();
        this.mWaiter = new Waiter();
        this.mCleaner = new Cleaner();
    }

    public void order() {
        mWaiter.order();
        mChef.cook();
        mCleaner.wash();
    }
}

class VegVendor {
    public void purchase() {
        System.out.println("买蔬菜");
    }
}

class Chef {
    public void cook() {
        System.out.println("做饭");
    }
}

class Waiter {
    public void order() {
        System.out.println("点单");
    }
}

class Cleaner {
    public void wash() {
        System.out.println("洗碗");
    }
}
