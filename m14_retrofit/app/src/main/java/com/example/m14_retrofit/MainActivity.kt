package com.example.m14_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.m14_retrofit.databinding.ActivityMainBinding
import com.example.m14_retrofit.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

   private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)


        }
    }
