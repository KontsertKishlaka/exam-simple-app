package com.example.kval_exam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        // Обработчики кнопок
        findViewById<Button>(R.id.profileButton).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        findViewById<Button>(R.id.mediaButton).setOnClickListener {
            startActivity(Intent(this, MediaActivity::class.java))
        }

        findViewById<Button>(R.id.randomNumberButton).setOnClickListener {
            startActivity(Intent(this, RandomNumberActivity::class.java))
        }

        findViewById<Button>(R.id.exitButton).setOnClickListener {
            finish()
        }
    }
}