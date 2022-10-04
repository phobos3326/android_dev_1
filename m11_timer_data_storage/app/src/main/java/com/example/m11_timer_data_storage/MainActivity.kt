package com.example.m11_timer_data_storage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.m11_timer_data_storage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}