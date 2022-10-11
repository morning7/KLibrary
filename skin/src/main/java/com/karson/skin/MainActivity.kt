package com.karson.skin

import android.os.Bundle
import com.karson.skin.databinding.ActivityMainBinding

class MainActivity : BaseSkinActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Question
         * 1.layoutInflater,layoutInflater.factory
         * 2.点击换肤遍历元素
         * 3.自定义控件和属性
         */
        binding.btnSwitch.setOnClickListener {
            binding.btnSwitch.postDelayed({
                onThemeUpdate()
            }, 500)
        }
    }

}