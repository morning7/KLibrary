package com.karson.device

import android.app.Application
import com.github.gzuliyujiang.oaid.DeviceIdentifier

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DeviceIdentifier.register(this)
    }
}