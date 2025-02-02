package com.example.m19_location

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.m19_location.databinding.ActivityMainBinding
import com.example.m19_location.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}