package com.example.kval_exam

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

class MediaActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)

        mediaPlayer = MediaPlayer.create(this, R.raw.sleepy_beat)

        // Настройка видеоплеера
        val videoView = findViewById<VideoView>(R.id.videoView)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI("android.resource://$packageName/${R.raw.vampire_soulviewy_cat}".toUri())

        // Обработчики кнопок аудио
        findViewById<Button>(R.id.playButton).setOnClickListener {
            mediaPlayer.start()
            updateProgressBar()
        }

        findViewById<Button>(R.id.pauseButton).setOnClickListener {
            mediaPlayer.pause()
        }

        findViewById<Button>(R.id.stopButton).setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.prepare()
            findViewById<ProgressBar>(R.id.audioProgressBar).progress = 0
        }
    }

    private fun updateProgressBar() {
        val progressBar = findViewById<ProgressBar>(R.id.audioProgressBar)
        progressBar.max = mediaPlayer.duration

        object : CountDownTimer(mediaPlayer.duration.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                progressBar.progress = mediaPlayer.currentPosition
            }

            override fun onFinish() {
                progressBar.progress = mediaPlayer.duration
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}