package com.karson.language

import android.app.Application

class LanguageApp : Application() {

    override fun onCreate() {
        super.onCreate()
        LanguageManager.init(this)
    }
}