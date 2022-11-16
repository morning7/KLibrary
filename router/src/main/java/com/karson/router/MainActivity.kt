package com.karson.router

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.karson.router.databinding.ActivityMainBinding

@Route(path = "/router/FirstActivity")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tv.setOnClickListener {
            ARouter.getInstance().build("/test/second")
                .withString("id", "82b434a0")
                .navigation()
        }
    }
}