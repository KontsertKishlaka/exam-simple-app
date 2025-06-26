package com.example.kval_exam

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity для отображения splash-экрана при запуске приложения.
 * Автоматически переходит в MainMenuActivity после короткой задержки.
 */
class SplashActivity : AppCompatActivity() {

    private val splashDelayMs = 1500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed(
            { navigateToMainMenu() },
            splashDelayMs
        )
    }

    private fun navigateToMainMenu() {
        startActivity(Intent(this, MainMenuActivity::class.java))
        finish()
    }
}