package com.example.kval_exam

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener

class ProfileActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        prefs = getSharedPreferences("profile_prefs", MODE_PRIVATE)
        loadProfileData()

        // Устанавливаем слушатели для сохранения данных при изменении
        setEditTextListeners()
    }

    private fun loadProfileData() {
        findViewById<EditText>(R.id.nameEditText).setText(prefs.getString("name", ""))
        findViewById<EditText>(R.id.nickEditText).setText(prefs.getString("nick", ""))
        findViewById<EditText>(R.id.birthDateEditText).setText(prefs.getString("birth_date", ""))
        findViewById<EditText>(R.id.emailEditText).setText(prefs.getString("email", ""))
    }

    private fun setEditTextListeners() {
        val editor = prefs.edit()

        findViewById<EditText>(R.id.nameEditText).addTextChangedListener {
            editor.putString("name", it.toString()).apply()
        }

        findViewById<EditText>(R.id.nickEditText).addTextChangedListener {
            editor.putString("nick", it.toString()).apply()
        }

        findViewById<EditText>(R.id.birthDateEditText).addTextChangedListener {
            editor.putString("birth_date", it.toString()).apply()
        }

        findViewById<EditText>(R.id.emailEditText).addTextChangedListener {
            editor.putString("email", it.toString()).apply()
        }
    }
}