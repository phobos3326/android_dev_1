package com.example.m17_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.m17_recyclerview.databinding.ActivityMainBinding
import com.example.m17_recyclerview.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}