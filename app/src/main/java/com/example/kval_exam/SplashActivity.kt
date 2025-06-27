package com.example.kval_exam

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

/**
 * Splash-экран приложения с анимированными облаками.
 *
 * Отображает три анимированных облака:
 * - cloudTop и cloudBottom выплывают из правой части экрана
 * - cloudMiddle выплывает из левой части экрана
 *
 * После завершения анимации (через 1500ms) автоматически переходит
 * на MainMenuActivity и завершает себя.
 */
class SplashActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_DELAY_MS = 1500L
    }

    private val cloudTop: ImageView by lazy { findViewById(R.id.cloudTop) }
    private val cloudMiddle: ImageView by lazy { findViewById(R.id.cloudMiddle) }
    private val cloudBottom: ImageView by lazy { findViewById(R.id.cloudBottom) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initAnimations()
        scheduleNavigation()
    }

    private fun initAnimations() {
        // Загружаем анимации
        val cloudTopAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_cloud_left)
        val cloudMiddleAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_cloud_right)
        val cloudBottomAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_cloud_left)

        // Запускаем анимации
        cloudTop.startAnimation(cloudTopAnimation)
        cloudMiddle.startAnimation(cloudMiddleAnimation)
        cloudBottom.startAnimation(cloudBottomAnimation)
    }

    private fun scheduleNavigation() {
        Handler(Looper.getMainLooper()).postDelayed(
            { navigateToMainMenu() },
            SPLASH_DELAY_MS
        )
    }

    /**
     * Переход на главный экран приложения (MainMenuActivity).
     */
    private fun navigateToMainMenu() {
        startActivity(Intent(this, MainMenuActivity::class.java))
        finish()
    }
}