package com.example.kval_exam

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import kotlin.random.Random

/**
 * Генератор случайных чисел в заданном диапазоне.
 *
 * Позволяет:
 * - Устанавливать верхнюю границу диапазона (счетчик)
 * - Генерировать случайное число от 0 до установленного значения
 * - Показывать результат в отдельном экране
 */
class RandomNumberActivity : AppCompatActivity() {
    private var counterValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_number)

        updateCounterText()

        // Инициализация кнопок
        initButtons()
    }

    private fun initButtons() {
        // Кнопка увеличения значения
        findViewById<AppCompatButton>(R.id.incrementButton).setOnClickListener {
            counterValue++
            updateCounterText()
        }

        // Кнопка уменьшения значения
        findViewById<AppCompatButton>(R.id.decrementButton).setOnClickListener {
            counterValue--
            updateCounterText()
        }

        // Кнопка генерации случайного числа
        findViewById<AppCompatButton>(R.id.getRandomNumberButton).setOnClickListener {
            if (counterValue != 0) {
                showRandomNumber()
            } else {
                Toast.makeText(this, "Установите значение отличное от 0", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateCounterText() {
        findViewById<TextView>(R.id.counterText).text = counterValue.toString()
    }

    private fun showRandomNumber() {
        val randomNumber = when {
            counterValue > 0 -> Random.nextInt(0, counterValue + 1)
            else -> Random.nextInt(counterValue, 1)
        }

        Intent(this, RandomNumberResultActivity::class.java).apply {
            putExtra("RANDOM_NUMBER", randomNumber)
            putExtra("MAX_VALUE", counterValue)
            startActivity(this)
        }
    }
}