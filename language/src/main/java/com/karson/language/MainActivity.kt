package com.karson.language

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text = findViewById<TextView>(R.id.text)

        text.postDelayed({
            text.text = LanguageManager.getString(R.string.program_wemedia_title)
        }, 1000)
    }
}