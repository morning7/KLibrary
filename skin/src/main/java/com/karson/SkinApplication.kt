package com.karson

import android.app.Application
import com.karson.skin.SkinManager

class SkinApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SkinManager.init(this, SkinManager.SKIN_NAME)
    }
}