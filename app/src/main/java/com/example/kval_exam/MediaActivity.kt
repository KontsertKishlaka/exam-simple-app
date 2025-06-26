package com.example.kval_exam

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.net.toUri

/**
 * Медиаплеер для воспроизведения аудио и видео.
 *
 * Функционал:
 * - Воспроизведение/пауза видео
 * - Воспроизведение/пауза/остановка аудио
 * - Прогресс-бар для аудио
 */
class MediaActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var videoView: VideoView
    private var isPlaying = false
    private lateinit var progressHandler: Handler
    private val progressRunnable = object : Runnable {
        override fun run() {
            updateProgressBar()
            progressHandler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)

        // Инициализация видео
        initVideoPlayer()

        // Инициализация аудио
        initAudioPlayer()

        // Настройка кнопок
        setupButtons()
    }

    private fun initVideoPlayer() {
        videoView = findViewById(R.id.videoView)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        // Установка URI видео (должен быть в res/raw/)
        val videoUri = "android.resource://$packageName/${R.raw.vampire_soulviewy_cat}".toUri()
        videoView.setVideoURI(videoUri)

        // Подготовка видео (но не автовоспроизведение)
        videoView.setOnPreparedListener { mp ->
            mp.isLooping = true
        }
    }

    private fun initAudioPlayer() {
        mediaPlayer = MediaPlayer.create(this, R.raw.sleepy_beat)
        findViewById<ProgressBar>(R.id.audioProgressBar).max = mediaPlayer.duration
        progressHandler = Handler(Looper.getMainLooper())
    }

    private fun setupButtons() {
        // Видео кнопки
        findViewById<AppCompatButton>(R.id.playVideoButton).setOnClickListener {
            if (!videoView.isPlaying) {
                videoView.start()
            }
        }

        findViewById<AppCompatButton>(R.id.pauseVideoButton).setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
            }
        }

        // Аудио кнопки
        findViewById<AppCompatButton>(R.id.playButton).setOnClickListener {
            if (!isPlaying) {
                mediaPlayer.start()
                isPlaying = true
                progressHandler.post(progressRunnable)
            }
        }

        findViewById<AppCompatButton>(R.id.pauseButton).setOnClickListener {
            if (isPlaying) {
                mediaPlayer.pause()
                isPlaying = false
                progressHandler.removeCallbacks(progressRunnable)
            }
        }

        findViewById<AppCompatButton>(R.id.stopButton).setOnClickListener {
            if (isPlaying) {
                mediaPlayer.apply {
                    pause()
                    seekTo(0)
                }
                isPlaying = false
                progressHandler.removeCallbacks(progressRunnable)
                findViewById<ProgressBar>(R.id.audioProgressBar).progress = 0
            }
        }
    }

    private fun updateProgressBar() {
        findViewById<ProgressBar>(R.id.audioProgressBar).progress = mediaPlayer.currentPosition
    }

    override fun onDestroy() {
        super.onDestroy()
        progressHandler.removeCallbacks(progressRunnable)
        mediaPlayer.release()
        videoView.suspend()
    }
}