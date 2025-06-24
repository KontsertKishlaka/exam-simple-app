package com.example.kval_exam

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RandomNumberResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_number_result)

        val randomNumber = intent.getIntExtra("RANDOM_NUMBER", 0)
        val maxValue = intent.getIntExtra("MAX_VALUE", 0)

        findViewById<TextView>(R.id.randomNumberText).text =
            "Это случайное число между 0 и $maxValue\n\n$randomNumber"
    }
}