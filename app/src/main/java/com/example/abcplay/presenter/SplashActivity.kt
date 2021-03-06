package com.example.abcplay.presenter

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.abcplay.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed(this::mostrarMainActivity, 3000)
    }

    private fun mostrarMainActivity() {
        val intent = Intent(
                this@SplashActivity, MainActivity::class.java
        )
        startActivity(intent)
        finish()
    }
}