package com.example.kval_exam

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

/**
 * Главное меню приложения с навигацией по основным функциям.
 *
 * Содержит кнопки для перехода:
 * - Профиль пользователя
 * - Медиаплеер
 * - Генератор случайных чисел
 * - Выход из приложения
 */
class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        // Обработчики кнопок
        findViewById<AppCompatButton>(R.id.profileButton).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        findViewById<AppCompatButton>(R.id.mediaButton).setOnClickListener {
            startActivity(Intent(this, MediaActivity::class.java))
        }

        findViewById<AppCompatButton>(R.id.randomNumberButton).setOnClickListener {
            startActivity(Intent(this, RandomNumberActivity::class.java))
        }

        findViewById<AppCompatButton>(R.id.exitButton).setOnClickListener {
            finish()
        }
    }
}