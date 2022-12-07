package com.karson.klibrary

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.karson.klibrary.databinding.ActivityMainBinding
import com.karson.klibrary.rxlogin.LoginSubjectFragment

class MainActivity : AppCompatActivity() {

    companion object {
        var isLogin = false
    }

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            if (!isLogin) {
                LoginSubjectFragment().create(this).login().subscribe {
                    if (it) {
                        isLogin = it
                        binding.btnLogin.performClick()
                    }
                }
                return@setOnClickListener
            }
            binding.btnLogin.text = "Login Success"
        }
    }

}