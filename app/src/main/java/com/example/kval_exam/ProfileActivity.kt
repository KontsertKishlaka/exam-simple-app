package com.example.kval_exam

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import java.io.File
import java.io.FileOutputStream
import androidx.core.content.edit

class ProfileActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences
    private lateinit var profileImage: ImageView
    private val imageFile by lazy { File(filesDir, "profile_image.jpg") }

    private val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                try {
                    val bitmap = loadScaledBitmap(uri)
                    saveBitmapToInternalStorage(bitmap)
                    profileImage.setImageBitmap(bitmap)
                    prefs.edit { putBoolean("has_profile_image", true) }
                } catch (e: Exception) {
                    Toast.makeText(this, "Ошибка обработки изображения", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        prefs = getSharedPreferences("profile_prefs", MODE_PRIVATE)
        profileImage = findViewById(R.id.profileImage)

        loadProfileData()
        setupListeners()
    }

    private fun loadProfileData() {
        // Загрузка текстовых данных
        findViewById<EditText>(R.id.nameEditText).setText(prefs.getString("name", ""))
        findViewById<EditText>(R.id.nickEditText).setText(prefs.getString("nick", ""))
        findViewById<EditText>(R.id.birthDateEditText).setText(prefs.getString("birth_date", ""))
        findViewById<EditText>(R.id.emailEditText).setText(prefs.getString("email", ""))

        // Загрузка изображения
        if (prefs.getBoolean("has_profile_image", false) && imageFile.exists()) {
            val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
            profileImage.setImageBitmap(bitmap)
        }
    }

    private fun setupListeners() {
        // Обработчик клика на изображение
        profileImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                type = "image/*"
            }
            getContent.launch(intent)
        }

        // Слушатели изменений текста
        findViewById<EditText>(R.id.nameEditText).addTextChangedListener {
            it?.toString()?.let { text -> prefs.edit { putString("name", text) } }
        }

        findViewById<EditText>(R.id.nickEditText).addTextChangedListener {
            it?.toString()?.let { text -> prefs.edit { putString("nick", text) } }
        }

        findViewById<EditText>(R.id.birthDateEditText).addTextChangedListener {
            it?.toString()?.let { text -> prefs.edit { putString("birth_date", text) } }
        }

        findViewById<EditText>(R.id.emailEditText).addTextChangedListener {
            it?.toString()?.let { text -> prefs.edit { putString("email", text) } }
        }
    }

    private fun loadScaledBitmap(uri: Uri): Bitmap {
        val inputStream = contentResolver.openInputStream(uri)
        val options = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
        }
        BitmapFactory.decodeStream(inputStream, null, options)
        inputStream?.close()

        // Вычисляем коэффициент масштабирования
        val scaleFactor = calculateInSampleSize(options, 500, 500)

        val newOptions = BitmapFactory.Options().apply {
            inSampleSize = scaleFactor
        }
        return BitmapFactory.decodeStream(contentResolver.openInputStream(uri), null, newOptions)
            ?: throw Exception("Failed to decode image")
    }

    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    private fun saveBitmapToInternalStorage(bitmap: Bitmap) {
        FileOutputStream(imageFile).use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
        }
    }

    override fun onDestroy() {
        // Очищаем ресурсы
        profileImage.setImageDrawable(null)
        super.onDestroy()
    }
}