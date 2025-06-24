package com.example.kval_exam

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Через 1.5 секунды переходим в основное меню
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainMenuActivity::class.java))
            finish()
        }, 1500)
    }
}