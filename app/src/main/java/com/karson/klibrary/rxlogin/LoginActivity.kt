package com.karson.klibrary.rxlogin

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.karson.klibrary.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSuccess.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }

        binding.btnFailed.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}