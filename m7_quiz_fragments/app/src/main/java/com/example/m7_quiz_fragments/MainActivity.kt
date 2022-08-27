package com.example.m7_quiz_fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.m7_quiz_fragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }




}