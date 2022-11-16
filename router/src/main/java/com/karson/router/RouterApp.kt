package com.karson.router

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

class RouterApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}