package com.example.gdsctreasurehunt.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gdsctreasurehunt.databinding.ActivityWinBinding

class WinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWinBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}