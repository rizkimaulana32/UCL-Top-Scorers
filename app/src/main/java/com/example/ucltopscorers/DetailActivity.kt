package com.example.ucltopscorers

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ucltopscorers.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.detail) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()

        val dataTopScore = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(ListTopScoreAdapter.KEY_TS, TopScore::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(ListTopScoreAdapter.KEY_TS)
        }

        if (dataTopScore != null) {
            binding.ivTsImg.setImageResource(dataTopScore.img)
            binding.tvTsName.text = dataTopScore.name
            binding.tvTsGoals.text = "${dataTopScore.goal} Goals"
            binding.tvTsDesc.text = dataTopScore.description
        }

        binding.actionShare.setOnClickListener {
            if (dataTopScore != null) {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Name: ${dataTopScore.name}\nGoals: ${dataTopScore.goal}\nDescription: ${dataTopScore.description}"
                    )
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }
        }
    }
}
