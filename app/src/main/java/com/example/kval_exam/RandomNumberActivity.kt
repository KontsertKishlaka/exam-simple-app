package com.example.kval_exam

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class RandomNumberActivity : AppCompatActivity() {
    private var counterValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_number)

        updateCounterText()

        // Обработчики кнопок
        findViewById<Button>(R.id.incrementButton).setOnClickListener {
            counterValue++
            updateCounterText()
        }

        findViewById<Button>(R.id.decrementButton).setOnClickListener {
            if (counterValue > 0) counterValue--
            updateCounterText()
        }

        findViewById<Button>(R.id.getRandomNumberButton).setOnClickListener {
            if (counterValue > 0) {
                val random = Random.nextInt(0, counterValue + 1)
                showRandomNumber(random)
            } else {
                Toast.makeText(this, "Установите значение больше 0", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateCounterText() {
        findViewById<TextView>(R.id.counterText).text = counterValue.toString()
    }

    private fun showRandomNumber(randomNumber: Int) {
        val intent = Intent(this, RandomNumberResultActivity::class.java).apply {
            putExtra("RANDOM_NUMBER", randomNumber)
            putExtra("MAX_VALUE", counterValue)
        }
        startActivity(intent)
    }
}