package com.karson.klibrary.splash

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val xdpi = resources.displayMetrics.xdpi
//        val ydpi = resources.displayMetrics.ydpi
//        Log.e("========","$xdpi $ydpi")

        val widthPx = resources.displayMetrics.widthPixels
        val heightPx = resources.displayMetrics.heightPixels
        Log.e("========","$widthPx $heightPx")
    }
}