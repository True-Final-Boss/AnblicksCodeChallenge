package com.example.anblickscodechallenge.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.anblickscodechallenge.R
class ChannelActivity : AppCompatActivity() {

        private val channelIv: ImageView by lazy { findViewById(R.id.channelIv) }
        private val tvTitle: TextView by lazy { findViewById(R.id.titleTv) }
        private val tvDj: TextView by lazy { findViewById(R.id.djTv) }
        private val tvDjMail: TextView by lazy { findViewById(R.id.djMailTv) }
        private val tvListeners: TextView by lazy { findViewById(R.id.listenersTv) }
        private val tvGenre: TextView by lazy { findViewById(R.id.genreTv) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channel)

        intent?.let {
            Glide.with(this)
                .load(intent.getStringExtra("imageData"))
                .into(channelIv)
            tvTitle.text = intent.getStringExtra("titleData")
            tvDj.text = intent.getStringExtra("djData")
            tvDjMail.text = intent.getStringExtra("djMailData")
            tvListeners.text = intent.getStringExtra("listenersData")
            tvGenre.text = intent.getStringExtra("genreData")
        }
    }
}
