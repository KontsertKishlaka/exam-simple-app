package com.example.kval_exam

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RandomNumberResultActivity : AppCompatActivity() {
    /**
     * Activity для отображения случайного числа и диапазона
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_number_result)

        // Получаем данные из Intent
        val randomNumber = intent.getIntExtra("RANDOM_NUMBER", 0)
        val maxValue = intent.getIntExtra("MAX_VALUE", 0)

        // Находим View элементы
        val rangeTextView = findViewById<TextView>(R.id.rangeText)
        val resultTextView = findViewById<TextView>(R.id.resultText)

        // Устанавливаем значения
        rangeTextView.text = String.format(getString(R.string.random_number_range), maxValue)
        resultTextView.text = randomNumber.toString()
    }
}