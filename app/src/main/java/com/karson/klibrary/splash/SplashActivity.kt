package com.karson.klibrary.splash

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.window.SplashScreen
import android.window.SplashScreenView
import androidx.appcompat.app.AppCompatActivity
import com.karson.klibrary.R

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val widthPx = resources.displayMetrics.widthPixels
        val heightPx = resources.displayMetrics.heightPixels
        Log.e("========","$widthPx $heightPx")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener {
            }
        }
    }
}