package com.karson.skin

import android.os.Bundle
import com.karson.skin.databinding.ActivityMainBinding

class MainActivity : BaseSkinActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSwitch.setOnClickListener {
            binding.btnSwitch.postDelayed({
                onThemeUpdate()
            }, 500)
        }
    }
}